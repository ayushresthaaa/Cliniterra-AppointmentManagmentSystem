package com.cliniterra.model;

import java.time.LocalDate;

public class DoctorModel {
    private int docId;
    private String docFirstName;
    private String docLastName;
    private String docGender;
    private String docEmail;
    private String docPhone;
    private LocalDate docDob;
    private String docSpeciality;
    private String docAddress;
    
    public DoctorModel() {}

    public DoctorModel(String docFirstName, String docLastName, String docGender, String docEmail, String docPhone, LocalDate docDob, String docSpeciality, String docAddress) {
        this.docFirstName = docFirstName;
        this.docLastName = docLastName;
        this.docGender = docGender;
        this.docEmail = docEmail;
        this.docPhone = docPhone;
        this.docDob = docDob;
        this.docSpeciality = docSpeciality;
        this.docAddress = docAddress;
    }

    // Getters and setters
    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getDocFirstName() {
        return docFirstName;
    }

    public void setDocFirstName(String docFirstName) {
        this.docFirstName = docFirstName;
    }

    public String getDocLastName() {
        return docLastName;
    }

    public void setDocLastName(String docLastName) {
        this.docLastName = docLastName;
    }

    public String getDocGender() {
        return docGender;
    }

    public void setDocGender(String docGender) {
        this.docGender = docGender;
    }

    public String getDocEmail() {
        return docEmail;
    }

    public void setDocEmail(String docEmail) {
        this.docEmail = docEmail;
    }

    public String getDocPhone() {
        return docPhone;
    }

    public void setDocPhone(String docPhone) {
        this.docPhone = docPhone;
    }

    public LocalDate getDocDob() {
        return docDob;
    }

    public void setDocDob(LocalDate docDob) {
        this.docDob = docDob;
    }

    public String getDocSpeciality() {
        return docSpeciality;
    }

    public void setDocSpeciality(String docSpeciality) {
        this.docSpeciality = docSpeciality;
    }

    public String getDocAddress() {
        return docAddress;
    }

    public void setDocAddress(String docAddress) {
        this.docAddress = docAddress;
    }
}
