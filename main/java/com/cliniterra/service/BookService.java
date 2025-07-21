package com.cliniterra.service;

import com.cliniterra.model.BookScheduleModel;
import com.cliniterra.config.Dbconfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    public List<BookScheduleModel> getSchedulesForDoctor(int docId) {
        List<BookScheduleModel> scheduleList = new ArrayList<>();
        String sql = "SELECT s.schedule_id, s.schedule_day, s.start_time, s.end_time " +
                     "FROM schedule s " +
                     "JOIN doc_schedule ds ON s.schedule_id = ds.schedule_id " +
                     "WHERE ds.doc_id = ?";
        try (
            Connection conn = Dbconfig.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, docId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BookScheduleModel sch = new BookScheduleModel();
                sch.setSchedule_id(rs.getInt("schedule_id"));
                sch.setSchedule_day(rs.getString("schedule_day"));
                sch.setStart_time(rs.getString("start_time"));
                sch.setEnd_time(rs.getString("end_time"));
                scheduleList.add(sch);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }

    // Check if user has ongoing booking
    public boolean hasOngoingBooking(int userId) {
        String sql = "SELECT COUNT(*) FROM user_appointment ua " +
                     "JOIN appointment a ON ua.appointment_id = a.appointment_id " +
                     "WHERE ua.user_id = ? AND a.appointment_status = 'Not Completed'";
        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Book the appointment with error message or null on success
    public String bookAppointmentWithError(int userId, int docId, int scheduleId, String appointmentDate) {
        if (hasOngoingBooking(userId)) {
            return "You already have an ongoing appointment. Please complete or cancel it before booking a new one.";
        }

        Connection conn = null;
        PreparedStatement psAppointment = null;
        ResultSet rs = null;

        try {
            conn = Dbconfig.getDbConnection();
            conn.setAutoCommit(false);

            String insertAppointment = "INSERT INTO appointment (appointment_date, appointment_status) VALUES (?, ?)";
            psAppointment = conn.prepareStatement(insertAppointment, Statement.RETURN_GENERATED_KEYS);
            psAppointment.setString(1, appointmentDate);
            psAppointment.setString(2, "Not Completed");
            psAppointment.executeUpdate();

            rs = psAppointment.getGeneratedKeys();
            int appointmentId = 0;
            if (rs.next()) {
                appointmentId = rs.getInt(1);
            } else {
                conn.rollback();
                return "Could not retrieve generated appointment ID.";
            }

            String insertAppSchedule = "INSERT INTO appointment_schedule (appointment_id, schedule_id) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertAppSchedule)) {
                ps.setInt(1, appointmentId);
                ps.setInt(2, scheduleId);
                ps.executeUpdate();
            }

            String insertUserAppointment = "INSERT INTO user_appointment (user_id, appointment_id, doc_id) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertUserAppointment)) {
                ps.setInt(1, userId);
                ps.setInt(2, appointmentId);
                ps.setInt(3, docId);
                ps.executeUpdate();
            }

            conn.commit();
            return null; // success

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return e.getMessage();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (psAppointment != null) psAppointment.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
