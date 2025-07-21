package com.cliniterra.service;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.DoctorModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorServiceAdmin {

    /**
     * Fetch all doctors from the database.
     */
    public List<DoctorModel> getAllDoctors() {
        List<DoctorModel> list = new ArrayList<>();

        String sql = """
            SELECT 
                doc_id,
                doc_first_name,
                doc_last_name,
                doc_gender,
                doc_email,
                doc_phone,
                doc_dob,
                doc_speciality,
                doc_address
            FROM doctor
            ORDER BY doc_last_name, doc_first_name
            """;

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DoctorModel d = new DoctorModel();
                d.setDocId(rs.getInt("doc_id"));
                d.setDocFirstName(rs.getString("doc_first_name"));
                d.setDocLastName(rs.getString("doc_last_name"));
                d.setDocGender(rs.getString("doc_gender"));
                d.setDocEmail(rs.getString("doc_email"));
                d.setDocPhone(rs.getString("doc_phone"));
                Date dob = rs.getDate("doc_dob");
                if (dob != null) {
                    d.setDocDob(dob.toLocalDate());
                }
                d.setDocSpeciality(rs.getString("doc_speciality"));
                d.setDocAddress(rs.getString("doc_address"));

                list.add(d);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}
