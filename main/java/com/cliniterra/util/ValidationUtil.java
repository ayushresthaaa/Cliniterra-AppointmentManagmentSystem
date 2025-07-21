package com.cliniterra.util;
//
///**
// * Utility class for input validation.
// * @author Seer pant 
// */

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import jakarta.servlet.http.Part;

public class ValidationUtil{
	
	// validate the null or empty values
	public static boolean isNullempty(String val) {
		return val== null || val.trim().isEmpty(); 
	}
	
	//validate alphabetic using regex
	public static boolean isAlphabetic(String val) {
		return !isNullempty(val) && val.matches("^[a-zA-Z]+$"); 
	}
	
	public static boolean isAlphanumericStartingWithLetter(String value) {
		return !isNullempty(value) && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}
	
	public static boolean isValidGender(String value) {
		return !isNullempty(value) && (value.equalsIgnoreCase("male") || value.equalsIgnoreCase("female"));
	}
	
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return !isNullempty(email) && Pattern.matches(emailRegex, email);
	}
	
	public static boolean isValidPhoneNumber(String number) {
		return !isNullempty(number) && number.matches("^98\\d{8}$");
	}
	
	public static boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return !isNullempty(password) && password.matches(passwordRegex);
	}
	
	public static boolean isValidImageExtension(Part imagePart) {
		if (imagePart == null || isNullempty(imagePart.getSubmittedFileName())) {
			return false;
		}
		String fileName = imagePart.getSubmittedFileName().toLowerCase();
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
				|| fileName.endsWith(".gif");
	}
	
	public static boolean doPasswordsMatch(String password, String retypePassword) {
		return !isNullempty(password) && !isNullempty(retypePassword) && password.equals(retypePassword);
	}
	
	public static boolean isAgeAtLeast12(LocalDate dob) {
		if (dob == null) {
			return false;
		}
		LocalDate today = LocalDate.now();
		return Period.between(dob, today).getYears() >= 12;
	}
	
}