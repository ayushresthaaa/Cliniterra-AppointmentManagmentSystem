package com.cliniterra.controller;

import com.cliniterra.model.ViewAppointmentModel;
import com.cliniterra.service.ViewAppointmentService;
import com.cliniterra.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/view" })
public class ViewAppointmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ViewAppointmentService appointmentService;

    public ViewAppointmentController() {
        super();
        appointmentService = new ViewAppointmentService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = (String) SessionUtil.getAt(req, "username");

        if (username == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        List<ViewAppointmentModel> appointments = appointmentService.getAppointmentsByUsername(username);

        if (appointments != null) {
            req.setAttribute("appointments", appointments);
        } else {
            req.setAttribute("error", "Error fetching appointments.");
        }
        req.getRequestDispatcher("WEB-INF/pages/viewappointment.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
