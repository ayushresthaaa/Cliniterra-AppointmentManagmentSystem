package com.cliniterra.service;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.ViewAppointmentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAppointmentService {

    public List<ViewAppointmentModel> getAppointmentsByUsername(String username) {
        List<ViewAppointmentModel> list = new ArrayList<>();

        String sql = """
            SELECT
              a.appointment_id,
              a.appointment_date,
              d.doc_first_name,
              d.doc_last_name,
              d.doc_speciality,
              s.schedule_day,
              s.start_time,
              s.end_time
            FROM appointment a
            JOIN appointment_schedule aps ON a.appointment_id = aps.appointment_id
            JOIN doc_schedule ds ON aps.schedule_id = ds.schedule_id
            JOIN doctor d ON ds.doc_id = d.doc_id
            JOIN schedule s ON aps.schedule_id = s.schedule_id
            JOIN user_appointment ua ON a.appointment_id = ua.appointment_id
            JOIN users u ON ua.user_id = u.user_id
            WHERE u.username = ?
              AND a.appointment_date >= CURDATE()
            ORDER BY a.appointment_date, s.start_time
            """;

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ViewAppointmentModel appointment = new ViewAppointmentModel();
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setAppointmentDate(rs.getTimestamp("appointment_date"));

                String doctorName = "Dr. " 
                    + rs.getString("doc_first_name") + " " 
                    + rs.getString("doc_last_name") + " (" 
                    + rs.getString("doc_speciality") + ")";
                appointment.setDoctorName(doctorName);

                appointment.setScheduleDay(rs.getString("schedule_day"));
                appointment.setStartTime(rs.getString("start_time"));
                appointment.setEndTime(rs.getString("end_time"));

                list.add(appointment);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }
}
