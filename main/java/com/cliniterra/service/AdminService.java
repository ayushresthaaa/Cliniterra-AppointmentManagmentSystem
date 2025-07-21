// src/main/java/com/cliniterra/service/AdminService.java
package com.cliniterra.service;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.AdminModel;

import java.sql.*;

public class AdminService {
    private Connection dbCon;

    public AdminService() {
        try {
            dbCon = Dbconfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public AdminModel getAdminByUsername(String username) {
        String query = 
            "SELECT * FROM users " +
            "WHERE username = ? AND user_type = 'admin'";
        try (PreparedStatement stmt = dbCon.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    AdminModel admin = new AdminModel();
                    admin.setUserName( rs.getString("username") );
                    admin.setFirstName( rs.getString("first_name") );
                    admin.setLastName(  rs.getString("last_name") );
                    admin.setGender(    rs.getString("user_gender") );
                    admin.setNumber(    rs.getString("user_phone") );
                    Date dob = rs.getDate("user_dob");
                    if (dob != null) {
                        admin.setDob(dob.toLocalDate());
                    }
                    admin.setEmail(    rs.getString("user_email") );
                    admin.setPassword( rs.getString("user_password") );
                    admin.setImageUrl( rs.getString("image_path") );
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAdmin(AdminModel admin) {
        String query =
            "UPDATE users SET " +
            "  first_name = ?, " +
            "  last_name  = ?, " +
            "  user_phone = ?, " +
            "  user_dob   = ?, " +
            "  user_email = ? " +
            "WHERE username = ? AND user_type = 'admin'";
        try (PreparedStatement stmt = dbCon.prepareStatement(query)) {
            stmt.setString(1, admin.getFirstName());
            stmt.setString(2, admin.getLastName());
            stmt.setString(3, admin.getNumber());
            stmt.setDate(4, Date.valueOf(admin.getDob()));
            stmt.setString(5, admin.getEmail());
            stmt.setString(6, admin.getUserName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
