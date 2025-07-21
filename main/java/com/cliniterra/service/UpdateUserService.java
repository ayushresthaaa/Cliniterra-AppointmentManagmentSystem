package com.cliniterra.service;
/**
 * @author Seer Pant
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.UserModel;
public class UpdateUserService {
	
	//do the database connection 
	 private Connection dbConn;

	    public UpdateUserService() {
	    	try {
				this.dbConn = Dbconfig.getDbConnection();
			} catch (SQLException | ClassNotFoundException ex) {
				System.err.println("Database connection error: " + ex.getMessage());
				ex.printStackTrace();
			}
	    }
	
	    public boolean updateUser(UserModel user) {
	    	
	        String query = "UPDATE users SET first_name=?, last_name=?, dob=?, gender=?, email=?, number=?, image_path=? WHERE username=?";
	        
	        try (PreparedStatement ppst = dbConn.prepareStatement(query)) {
	            ppst.setString(1, user.getFirstName());
	            ppst.setString(2, user.getLastName());
	            ppst.setDate(3, Date.valueOf(user.getDob()));
	            ppst.setString(4, user.getGender());
	            ppst.setString(5, user.getEmail());
	            ppst.setString(6, user.getNumber());
	            ppst.setString(7, user.getImageUrl());
	            ppst.setString(8, user.getUserName());
	            
	            return ppst.executeUpdate() > 0;
	        } 
	        catch (SQLException e) {
	            System.err.println("Error to update user " + e.getMessage());
	            e.printStackTrace();
	            return false;
	        }
	    }
}
