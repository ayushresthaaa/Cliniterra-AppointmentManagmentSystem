package com.cliniterra.model;

/**
 * @author Seer Pant
 */
import java.time.LocalDate;

public class UserModel {

	private String firstName;
	private String lastName;
	private String userName;
	private LocalDate dob;
	private String gender;
	private String email;
	private String number;
	private String password;
	private String imageUrl;
	
	
	//this is for login
	public UserModel() { 
		
	}
	public UserModel(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	
	//this is for registration
	public UserModel(String firstName, String lastName, String userName, LocalDate dob, String gender, String email,
			String number, String password, String imageUrl) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.number = number;
		this.password = password;
		this.imageUrl = imageUrl;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public LocalDate getDob() {
		return dob;
	}




	public void setDob(LocalDate dob) {
		this.dob = dob;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getNumber() {
		return number;
	}




	public void setNumber(String number) {
		this.number = number;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getImageUrl() {
		return imageUrl;
	}




	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
	
}
