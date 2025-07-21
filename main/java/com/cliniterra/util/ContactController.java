//package com.cliniterra.util;
//
//import com.cliniterra.model.FeedbackModel;
//import com.cliniterra.service.FeedbackService;
//import com.cliniterra.util.SessionUtil;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.time.LocalDate;
//
//public class ContactController extends HttpServlet {
//
//    private FeedbackService feedbackService;
//
//    public ContactController() {
//        this.feedbackService = new FeedbackService(); // Initialize FeedbackService
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        // Fetching the session username using SessionUtil
//        String username = (String) SessionUtil.getAt(req, "username");
//        
//        // If the username is not found, redirect to login page
//        if (username == null) {
//            res.sendRedirect("login.jsp");
//            return;
//        }
//
//        // Getting feedback description from the form data
//        String feedbackDesc = req.getParameter("feedbackDesc");
//
//        // Get the current date
//        LocalDate feedbackDate = LocalDate.now();
//
//        // Create a FeedbackModel instance with the captured data
//        FeedbackModel feedback = new FeedbackModel(feedbackDesc, feedbackDate);
//
//        // Adding the feedback by passing the username and feedback model to the service
//        boolean added = feedbackService.addFeedbackByUsername(username, feedback);
//
//        // Setting a success or error message based on the feedback submission result
//        if (added) {
//            req.setAttribute("success", "Feedback submitted successfully!");
//        } else {
//            req.setAttribute("error", "Failed to submit feedback.");
//        }
//
//        // Forwarding the request to the contact page with the status message
//        req.getRequestDispatcher("/WEB-INF/pages/contact.jsp").forward(req, res);
//    }
//}
