// src/com/cliniterra/service/FindDocService.java
package com.cliniterra.service;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.DoctorModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for CRUD on doctors (and cleaning up their related data).
 */
public class FindDocService {

    private Connection dbCon;
    private boolean isConnectionError = false;

    public FindDocService() {
        try {
            dbCon = Dbconfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Fetches all doctors for display in dropdowns, etc.
     */
    public List<DoctorModel> getAllDoctors() throws SQLException {
        List<DoctorModel> doctors = new ArrayList<>();
        if (isConnectionError) return doctors;

        String sql = ""
            + "SELECT "
            + "  doc_id          AS docId, "
            + "  doc_first_name  AS docFirstName, "
            + "  doc_last_name   AS docLastName, "
            + "  doc_gender      AS docGender, "
            + "  doc_email       AS docEmail, "
            + "  doc_phone       AS docPhone, "
            + "  doc_dob         AS docDob, "
            + "  doc_speciality  AS docSpeciality, "
            + "  doc_address     AS docAddress "
            + "FROM doctor";

        try (PreparedStatement ps = dbCon.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DoctorModel doc = new DoctorModel();
                doc.setDocId(rs.getInt("docId"));
                doc.setDocFirstName(rs.getString("docFirstName"));
                doc.setDocLastName(rs.getString("docLastName"));
                doc.setDocGender(rs.getString("docGender"));
                doc.setDocEmail(rs.getString("docEmail"));
                doc.setDocPhone(rs.getString("docPhone"));
                Date d = rs.getDate("docDob");
                if (d != null) doc.setDocDob(d.toLocalDate());
                doc.setDocSpeciality(rs.getString("docSpeciality"));
                doc.setDocAddress(rs.getString("docAddress"));
                doctors.add(doc);
            }
        }

        return doctors;
    }

    /**
     * Inserts a new doctor record.
     * @return true if insert succeeded
     */
    public boolean addDoctor(DoctorModel doc) throws SQLException {
        if (isConnectionError) return false;

        String sql =
            "INSERT INTO doctor "
          + "(doc_first_name, doc_last_name, doc_gender, "
          + " doc_email, doc_phone, doc_dob, "
          + " doc_speciality, doc_address) "
          + "VALUES (?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = dbCon.prepareStatement(sql)) {
            ps.setString(1, doc.getDocFirstName());
            ps.setString(2, doc.getDocLastName());
            ps.setString(3, doc.getDocGender());
            ps.setString(4, doc.getDocEmail());
            ps.setString(5, doc.getDocPhone());
            if (doc.getDocDob() != null) {
                ps.setDate(6, Date.valueOf(doc.getDocDob()));
            } else {
                ps.setNull(6, Types.DATE);
            }
            ps.setString(7, doc.getDocSpeciality());
            ps.setString(8, doc.getDocAddress());
            return ps.executeUpdate() == 1;
        }
    }

    /**
     * Deletes a doctor and any linked appointments/schedule entries.
     */
    public void removeDoctor(int docId) throws SQLException {
        if (isConnectionError) {
            throw new SQLException("DB connection error");
        }

        // 1) delete appointments
        try (PreparedStatement ps = dbCon.prepareStatement(
                "DELETE FROM user_appointment WHERE doc_id = ?")) {
            ps.setInt(1, docId);
            ps.executeUpdate();
        }

        // 2) delete schedule links
        try (PreparedStatement ps = dbCon.prepareStatement(
                "DELETE FROM doc_schedule WHERE doc_id = ?")) {
            ps.setInt(1, docId);
            ps.executeUpdate();
        }

        // 3) delete doctor
        try (PreparedStatement ps = dbCon.prepareStatement(
                "DELETE FROM doctor WHERE doc_id = ?")) {
            ps.setInt(1, docId);
            ps.executeUpdate();
        }
    }
}
