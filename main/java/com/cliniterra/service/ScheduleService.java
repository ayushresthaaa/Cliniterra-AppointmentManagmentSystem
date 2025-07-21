package com.cliniterra.service;

import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.ScheduleModel;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleService {
    private Connection dbCon;
    private boolean connError = false;

    public ScheduleService() {
        try {
            dbCon = Dbconfig.getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
            connError = true;
        }
    }

    public List<ScheduleModel> getAllSchedules() throws SQLException {
        List<ScheduleModel> list = new ArrayList<>();
        if (connError) return list;

        String sql = "SELECT schedule_id, schedule_day, start_time, end_time, slot_num FROM schedule";
        try (PreparedStatement ps = dbCon.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ScheduleModel s = new ScheduleModel();
                s.setScheduleId(rs.getInt("schedule_id"));
                s.setScheduleDay(rs.getString("schedule_day"));
                Time t1 = rs.getTime("start_time"), t2 = rs.getTime("end_time");
                s.setStartTime(t1 != null ? t1.toLocalTime() : null);
                s.setEndTime(t2 != null ? t2.toLocalTime() : null);
                s.setSlotNum(rs.getInt("slot_num"));
                list.add(s);
            }
        }
        return list;
    }
}
