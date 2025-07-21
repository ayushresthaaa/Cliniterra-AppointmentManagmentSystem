package com.cliniterra.controller;
/**
 * @author Aayush Shrestha
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import com.cliniterra.model.FeedbackModel;
import com.cliniterra.service.FeedbackService;
import com.cliniterra.util.SessionUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contact" })
public class ContactController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final FeedbackService feedbackService = new FeedbackService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/contact.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String username = (String) SessionUtil.getAt(req, "username");
        
        
        if (username == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        String feedbackDesc = req.getParameter("feedbackDesc");
        String feedbackDateStr = req.getParameter("feedbackDate");

   
        LocalDate feedbackDate = null;
        try {
            feedbackDate = LocalDate.parse(feedbackDateStr);
        } catch (Exception e) {
            req.setAttribute("error", "Invalid date format. Please use yyyy-mm-dd.");
            req.getRequestDispatcher("/WEB-INF/pages/contact.jsp").forward(req, res);
            return;
        }

       
        FeedbackModel feedback = new FeedbackModel(feedbackDesc, feedbackDate);

       
        boolean added = feedbackService.addFeedbackByUsername(username, feedback);

        // Setting a success or error message based on the feedback submission result
        if (added) {
            req.setAttribute("success", "Feedback submitted successfully!");
        } else {
            req.setAttribute("error", "Failed to submit feedback.");
        }

        // Forwarding the request to the contact page with the status message
        req.getRequestDispatcher("/WEB-INF/pages/contact.jsp").forward(req, res);
    }
}
