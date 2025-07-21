package com.cliniterra.controller;

import com.cliniterra.model.UserAppointment;
import com.cliniterra.service.UserAppointmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = {"/admindashboard"})
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserAppointmentService userAppointmentService = new UserAppointmentService();

    public DashboardController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserAppointment> appointments = userAppointmentService.getAllUserAppointments();
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("WEB-INF/pages/admindashboard.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        if (username != null && !username.isBlank()) {
            userAppointmentService.deleteAppointmentByUsername(username);
        }
        // re-fetch and render remaining appointments
        doGet(request, response);
    }
}
