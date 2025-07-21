// src/main/java/com/cliniterra/model/AdminModel.java
package com.cliniterra.model;

import java.time.LocalDate;

public class AdminModel {
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String email;
    private String number;
    private String password;
    private String imageUrl;

    public AdminModel() { }

    public AdminModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public AdminModel(String firstName, String lastName, String userName,
                      LocalDate dob, String gender, String email,
                      String number, String password, String imageUrl) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.userName  = userName;
        this.dob       = dob;
        this.gender    = gender;
        this.email     = email;
        this.number    = number;
        this.password  = password;
        this.imageUrl  = imageUrl;
    }

    // getters & setters...
    public String getUserName()       { return userName; }
    public void   setUserName(String u){ userName = u; }
    public String getFirstName()      { return firstName; }
    public void   setFirstName(String f){ firstName = f; }
    public String getLastName()       { return lastName; }
    public void   setLastName(String l){ lastName = l; }
    public LocalDate getDob()         { return dob; }
    public void   setDob(LocalDate d){ dob = d; }
    public String getGender()         { return gender; }
    public void   setGender(String g){ gender = g; }
    public String getEmail()          { return email; }
    public void   setEmail(String e){ email = e; }
    public String getNumber()         { return number; }
    public void   setNumber(String n){ number = n; }
    public String getPassword()       { return password; }
    public void   setPassword(String p){ password = p; }
    public String getImageUrl()       { return imageUrl; }
    public void   setImageUrl(String i){ imageUrl = i; }
}
