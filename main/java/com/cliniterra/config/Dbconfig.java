package com.cliniterra.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Aayush Shrestha
 */
public class Dbconfig {
	private static final String DB_NAME = "appointment_management";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
