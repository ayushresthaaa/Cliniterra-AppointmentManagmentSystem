package com.cliniterra.service;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.UserAppointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAppointmentService {

    public List<UserAppointment> getAllUserAppointments() {
        List<UserAppointment> list = new ArrayList<>();

        String sql = """
            SELECT 
                u.user_id, u.username, u.first_name, u.last_name, 
                a.appointment_id, a.appointment_date, a.appointment_status 
            FROM users u 
            JOIN user_appointment ua ON u.user_id = ua.user_id
            JOIN appointment a ON ua.appointment_id = a.appointment_id
            ORDER BY a.appointment_date ASC
            """;

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UserAppointment ua = new UserAppointment(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("appointment_id"),
                    rs.getTimestamp("appointment_date"),
                    rs.getString("appointment_status")
                );
                list.add(ua);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public boolean deleteAppointmentByUsername(String username) {
        String findSql = """
            SELECT a.appointment_id
            FROM appointment a
            JOIN user_appointment ua ON a.appointment_id = ua.appointment_id
            JOIN users u            ON ua.user_id = u.user_id
            WHERE u.username = ?
            """;

        String delUA = "DELETE FROM user_appointment    WHERE appointment_id = ?";
        String delAS = "DELETE FROM appointment_schedule WHERE appointment_id = ?";
        String delA  = "DELETE FROM appointment         WHERE appointment_id = ?";

        try (Connection conn = Dbconfig.getDbConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement findPs = conn.prepareStatement(findSql)) {
                findPs.setString(1, username);
                ResultSet rs = findPs.executeQuery();

                while (rs.next()) {
                    int appId = rs.getInt("appointment_id");
                    try (PreparedStatement ps1 = conn.prepareStatement(delUA);
                         PreparedStatement ps2 = conn.prepareStatement(delAS);
                         PreparedStatement ps3 = conn.prepareStatement(delA)) {

                        ps1.setInt(1, appId); ps1.executeUpdate();
                        ps2.setInt(1, appId); ps2.executeUpdate();
                        ps3.setInt(1, appId); ps3.executeUpdate();
                    }
                }
                conn.commit();
                return true;

            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
