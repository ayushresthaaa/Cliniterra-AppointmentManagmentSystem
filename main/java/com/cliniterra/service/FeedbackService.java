package com.cliniterra.service;
/**
 * @author Aayush Shrestha
 */
import com.cliniterra.config.Dbconfig;
import com.cliniterra.model.FeedbackModel;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;

/**
 * @author Aayush
 */
public class FeedbackService {
    private Connection dbcon;

    public FeedbackService() {
        try {
            this.dbcon = Dbconfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean addFeedbackByUsername(String username, FeedbackModel feedback) {
        String userIdQuery = "SELECT user_id FROM users WHERE username = ?";
        
        try (PreparedStatement ps = dbcon.prepareStatement(userIdQuery)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");

          
                String insertFeedbackQuery = "INSERT INTO feedback (feedback_desc, feedback_date) VALUES (?, ?)";
                try (PreparedStatement insertFeedbackStmt = dbcon.prepareStatement(insertFeedbackQuery, Statement.RETURN_GENERATED_KEYS)) {
                    insertFeedbackStmt.setString(1, feedback.getFeedbackDesc());
                    insertFeedbackStmt.setDate(2, Date.valueOf(feedback.getFeedbackDate()));

               
                    int rowsAffected = insertFeedbackStmt.executeUpdate();

                    if (rowsAffected > 0) {
                 
                        ResultSet generatedKeys = insertFeedbackStmt.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int feedbackId = generatedKeys.getInt(1);  

                            
                            String insertUserFeedbackQuery = "INSERT INTO user_feedback (user_id, feedback_id) VALUES (?, ?)";
                            try (PreparedStatement insertUserFeedbackStmt = dbcon.prepareStatement(insertUserFeedbackQuery)) {
                                insertUserFeedbackStmt.setInt(1, userId); 
                                insertUserFeedbackStmt.setInt(2, feedbackId); 

                               
                                return insertUserFeedbackStmt.executeUpdate() > 0;
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding feedback: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
