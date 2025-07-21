package com.cliniterra.model;

import java.sql.Timestamp;

public class ViewAppointmentModel {
    private int appointmentId;
    private Timestamp appointmentDate;
    private String doctorName;
    private String scheduleDay;
    private String startTime;
    private String endTime;

    // Getters and setters
    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public Timestamp getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Timestamp appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getScheduleDay() { return scheduleDay; }
    public void setScheduleDay(String scheduleDay) { this.scheduleDay = scheduleDay; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}
