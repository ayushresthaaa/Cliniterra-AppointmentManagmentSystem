package com.cliniterra.service;

import com.cliniterra.config.Dbconfig;

import java.sql.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DoctorScheduleService {
    private Connection dbCon;
    private boolean connError = false;

    public DoctorScheduleService() {
        try {
            dbCon = Dbconfig.getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
            connError = true;
        }
    }

    /**
     * Returns the schedule_id currently assigned to this doctor,
     * or -1 if none.
     */
    public int getScheduleIdByDoctor(int docId) throws SQLException {
        if (connError) return -1;

        String sql = "SELECT schedule_id FROM doc_schedule WHERE doc_id = ?";
        try (PreparedStatement ps = dbCon.prepareStatement(sql)) {
            ps.setInt(1, docId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("schedule_id") : -1;
            }
        }
    }

    /**
     * Returns the set of all schedule_id values that are already
     * assigned to any doctor.
     */
    public Set<Integer> getAllAssignedScheduleIds() throws SQLException {
        if (connError) return Collections.emptySet();

        String sql = "SELECT schedule_id FROM doc_schedule";
        Set<Integer> assigned = new HashSet<>();
        try (PreparedStatement ps = dbCon.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                assigned.add(rs.getInt("schedule_id"));
            }
        }
        return assigned;
    }

    /**
     * Inserts or updates a doctor's schedule in doc_schedule.
     * Requires a UNIQUE constraint on doc_schedule(doc_id).
     */
    public void updateDoctorSchedule(int docId, int scheduleId) throws SQLException {
        if (connError) throw new SQLException("DB connection error");

        String sql = 
          "INSERT INTO doc_schedule (doc_id, schedule_id) VALUES (?, ?) " +
          "ON DUPLICATE KEY UPDATE schedule_id = ?";
        try (PreparedStatement ps = dbCon.prepareStatement(sql)) {
            ps.setInt(1, docId);
            ps.setInt(2, scheduleId);
            ps.setInt(3, scheduleId);
            ps.executeUpdate();
        }
    }
}
