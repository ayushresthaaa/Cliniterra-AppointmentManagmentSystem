package com.cliniterra.model;

import java.util.Date;

public class UserAppointment {
    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private int appointmentId;
    private Date appointmentDate;
    private String appointmentStatus;

    public UserAppointment(int userId, String username, String firstName, String lastName,
                           int appointmentId, Date appointmentDate, String appointmentStatus) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentStatus = appointmentStatus;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAppointmentId() { return appointmentId; }
    public Date getAppointmentDate() { return appointmentDate; }
    public String getAppointmentStatus() { return appointmentStatus; }
}
