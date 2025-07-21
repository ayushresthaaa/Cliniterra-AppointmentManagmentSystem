// src/com/cliniterra/controller/RemoveDoctorController.java
package com.cliniterra.controller;

import com.cliniterra.service.FindDocService;
import com.cliniterra.model.DoctorModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = { "/removeDoctor" })
public class RemoveDoctorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final FindDocService service = new FindDocService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String param = req.getParameter("docId");
        if (param == null || param.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "No doctor selected");
            return;
        }

        try {
            int docId = Integer.parseInt(param);
            service.removeDoctor(docId);

            // after successful removal, redirect back to addDoc GET so it reloads the list
            resp.sendRedirect(req.getContextPath() + "/addDoc");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid doctor ID");
        } catch (SQLException e) {
            // In case of any DB error, reload the page with an error message
            req.setAttribute("error", "Unable to remove doctor: " + e.getMessage());
            try {
                // repopulate the doctor list
                List<DoctorModel> doctors = service.getAllDoctors();
                req.setAttribute("doctors", doctors);
            } catch (SQLException ex) {
                // ignore secondary error
            }
            req.getRequestDispatcher("/WEB-INF/pages/addDoctor.jsp")
               .forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Redirect GET to the main addDoc page
        resp.sendRedirect(req.getContextPath() + "/addDoc");
    }
}
