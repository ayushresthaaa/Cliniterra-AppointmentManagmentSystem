package com.cliniterra.service;
/**
 * @author Aayush Shrestha
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.UserModel;

public class RegistrationService {
	private Connection dbcon; 
	
	
	public RegistrationService() { 
		
		try {
			this.dbcon = Dbconfig.getDbConnection();
			
		}
		 catch (SQLException | ClassNotFoundException ex) {
				System.err.println("Database connection error: " + ex.getMessage());
				ex.printStackTrace();
			}
	}
	public Boolean addUser(UserModel user) {
		if (dbcon == null) {
			System.err.println("Database connection is not available.");
			return null;
		}
		
		String insertQuery = "INSERT INTO users (username, first_name, last_name, user_gender, user_phone, user_dob, user_type, user_email, user_password, image_path) " + 
		"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"	;
		
		try(PreparedStatement insertStmt = dbcon.prepareStatement(insertQuery)){
			insertStmt.setString(1, user.getUserName());
		    insertStmt.setString(2, user.getFirstName());
		    insertStmt.setString(3, user.getLastName());
		    insertStmt.setString(4, user.getGender());
		    insertStmt.setString(5, user.getNumber());
		    insertStmt.setDate(6, Date.valueOf(user.getDob()));
		    insertStmt.setString(7, "user");  // Default user type (can be adjusted as needed)
		    insertStmt.setString(8, user.getEmail());
		    insertStmt.setString(9, user.getPassword());
		    insertStmt.setString(10, user.getImageUrl());
		    
		    return insertStmt.executeUpdate() > 0;
		}
		catch (SQLException e) {
			System.err.println("Error during student registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
}



