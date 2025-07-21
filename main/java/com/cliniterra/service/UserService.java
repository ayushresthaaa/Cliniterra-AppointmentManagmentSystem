package com.cliniterra.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cliniterra.config.Dbconfig;

public class UserService {
    public int getUserIdByUsername(String username) {
        int userId = -1;
        String sql = "SELECT user_id FROM users WHERE username = ?";
        try (
            Connection conn = Dbconfig.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("user_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }
}
