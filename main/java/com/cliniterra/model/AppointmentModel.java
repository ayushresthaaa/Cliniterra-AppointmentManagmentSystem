package com.cliniterra.model;

import java.sql.Timestamp;

public class AppointmentModel {
    private int appointmentId;
    private Timestamp appointmentDate;
    private String appointmentStatus;
    private String username; // Associated user's username

    // Getters and setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
