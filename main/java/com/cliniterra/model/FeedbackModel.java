package com.cliniterra.model;
/**
 * @author Aayush Shrestha
 */
import java.time.LocalDate;

public class FeedbackModel {
    private int id;
    private String feedbackDesc;
    private LocalDate feedbackDate;

    public FeedbackModel(String feedbackDesc, LocalDate feedbackDate) {
        this.feedbackDesc = feedbackDesc;
        this.feedbackDate = feedbackDate;
    }

    public FeedbackModel(int id, String feedbackDesc, LocalDate feedbackDate) {
        this.id = id;
        this.feedbackDesc = feedbackDesc;
        this.feedbackDate = feedbackDate;
    }

    public int getId() {
        return id;
    }

    public String getFeedbackDesc() {
        return feedbackDesc;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }
}
