package com.cliniterra.service;
/**
 * @author Aayush Shrestha
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.UserModel;
import com.cliniterra.util.PasswordUtil;
public class LoginService {
	
	private Connection dbCon;
	private boolean isConnectionError = false;
		
	public LoginService() { 
		
		try {
			dbCon = Dbconfig.getDbConnection();
		}
		catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public Boolean loginUser(UserModel user) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT username, user_password FROM users WHERE username = ?";
		try (PreparedStatement stmt = dbCon.prepareStatement(query)) {
			stmt.setString(1, user.getUserName());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}
	
	private boolean validatePassword(ResultSet result, UserModel user) throws SQLException {
		String dbUsername = result.getString("username");
		String dbPassword = result.getString("user_password");

		 return dbUsername.equals(user.getUserName()) && 
				 dbPassword.equals(user.getPassword());
	}
	
}
