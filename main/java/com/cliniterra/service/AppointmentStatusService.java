package com.cliniterra.service;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.AppointmentStatusModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentStatusService {

    public List<AppointmentStatusModel> getAllAppointmentsWithUserStatus() {
        List<AppointmentStatusModel> list = new ArrayList<>();

        String sql = """
            SELECT a.appointment_id, u.user_id,
                   CONCAT(u.first_name, ' ', u.last_name) AS user_name,
                   u.email, u.phone, a.appointment_status, a.appointment_date
            FROM appointment a
            JOIN user_appointment ua ON a.appointment_id = ua.appointment_id
            JOIN users u ON ua.user_id = u.user_id
            ORDER BY a.appointment_date DESC
            """;

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AppointmentStatusModel asm = new AppointmentStatusModel();
                asm.setAppointmentId(rs.getInt("appointment_id"));
                asm.setUserId(rs.getInt("user_id"));
                asm.setUserName(rs.getString("user_name"));
                asm.setEmail(rs.getString("email"));
                asm.setPhone(rs.getString("phone"));
                asm.setAppointmentStatus(rs.getString("appointment_status"));
                asm.setAppointmentDate(rs.getTimestamp("appointment_date"));

                list.add(asm);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }
}
