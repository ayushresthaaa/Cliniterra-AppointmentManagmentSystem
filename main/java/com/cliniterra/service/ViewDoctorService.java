package com.cliniterra.service;

import com.cliniterra.model.DoctorModel;
import com.cliniterra.model.DoctorViewScheduleModel;
import com.cliniterra.config.Dbconfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewDoctorService {

    public DoctorModel getDoctorById(int docId) {
        DoctorModel doctor = null;
        String sql = "SELECT * FROM doctor WHERE doc_id = ?";
        try (
            Connection conn = Dbconfig.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, docId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doctor = new DoctorModel();
                doctor.setDocId(rs.getInt("doc_id"));
                doctor.setDocFirstName(rs.getString("doc_first_name"));
                doctor.setDocLastName(rs.getString("doc_last_name"));
                doctor.setDocGender(rs.getString("doc_gender"));
                doctor.setDocEmail(rs.getString("doc_email"));
                doctor.setDocPhone(rs.getString("doc_phone"));
                java.sql.Date sqlDob = rs.getDate("doc_dob");
                if (sqlDob != null) {
                    doctor.setDocDob(sqlDob.toLocalDate());
                }
                doctor.setDocSpeciality(rs.getString("doc_speciality"));
                doctor.setDocAddress(rs.getString("doc_address"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public List<DoctorViewScheduleModel> getScheduleByDoctorId(int docId) {
        List<DoctorViewScheduleModel> scheduleList = new ArrayList<>();
        String sql = "SELECT s.schedule_day, s.start_time, s.end_time " +
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
                DoctorViewScheduleModel sch = new DoctorViewScheduleModel();
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

}
