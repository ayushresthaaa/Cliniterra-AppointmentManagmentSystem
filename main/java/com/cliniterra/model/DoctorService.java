package com.cliniterra.model;

//package com.cliniterra.model;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.DoctorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorService {
    private Connection dbcon;

    public DoctorService() {
        try {
            this.dbcon = Dbconfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean addDoctor(DoctorModel doctor) {
        if (dbcon == null) return false;

        String sql = "INSERT INTO doctor (doc_first_name, doc_last_name, doc_gender, doc_email, doc_phone, doc_dob, doc_speciality, doc_address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = dbcon.prepareStatement(sql)) {
            ps.setString(1, doctor.getDocFirstName());
            ps.setString(2, doctor.getDocLastName());
            ps.setString(3, doctor.getDocGender());
            ps.setString(4, doctor.getDocEmail());
            ps.setString(5, doctor.getDocPhone());
            ps.setDate(6, java.sql.Date.valueOf(doctor.getDocDob()));
            ps.setString(7, doctor.getDocSpeciality());
            ps.setString(8, doctor.getDocAddress());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

