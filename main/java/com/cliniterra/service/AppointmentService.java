package com.cliniterra.service;
/**
 * @author Aayush Shrestha
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.AppointmentModel;

public class AppointmentService {

    private Connection dbCon;
    private boolean isConnectionError = false;

    public AppointmentService() {
        try {
            dbCon = Dbconfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public List<AppointmentModel> getAppointmentsByUsername(String username) {
        List<AppointmentModel> appointments = new ArrayList<>();

        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT a.appointment_id, a.appointment_date, a.appointment_status, u.username " +
                       "FROM appointment a " +
                       "JOIN user_appointment au ON a.appointment_id = au.appointment_id " +
                       "JOIN users u ON au.user_id = u.user_id " +
                       "WHERE u.username = ?";
        try (PreparedStatement stmt = dbCon.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                AppointmentModel appointment = new AppointmentModel();
                appointment.setAppointmentId(result.getInt("appointment_id"));
                appointment.setAppointmentDate(result.getTimestamp("appointment_date"));
                appointment.setAppointmentStatus(result.getString("appointment_status"));
                appointment.setUsername(result.getString("username")); // Set associated username
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return appointments;
    }

}
