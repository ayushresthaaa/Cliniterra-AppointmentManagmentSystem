package com.cliniterra.service;
/**
 * @author Aayush Shrestha
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.UserModel;

public class ViewProfileService {
	private Connection dbCon;

    public ViewProfileService() {
        try {
            dbCon = Dbconfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public UserModel getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = dbCon.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UserModel user = new UserModel();
                user.setUserName(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setGender(rs.getString("user_gender"));
                user.setNumber(rs.getString("user_phone"));
                Date sqlDob = rs.getDate("user_dob");
                if (sqlDob != null) {
                    user.setDob(sqlDob.toLocalDate());  // Converts java.sql.Date to LocalDate
                }
//                user.setUserType(rs.getString("user_type"));
                user.setEmail(rs.getString("user_email"));
                user.setPassword(rs.getString("user_password"));
                user.setImageUrl(rs.getString("image_path"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateUser(UserModel user) {
        String query = "UPDATE users SET first_name = ?, last_name = ?, user_phone = ?, user_dob = ?, user_email = ?  WHERE username = ?";
        try (PreparedStatement stmt = dbCon.prepareStatement(query)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getNumber());
            stmt.setDate(4, Date.valueOf(user.getDob()));
            stmt.setString(5, user.getEmail());
//            stmt.setString(7, user.getImageUrl());
            stmt.setString(6, user.getUserName());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
