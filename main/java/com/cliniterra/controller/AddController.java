// src/com/cliniterra/controller/AddController.java
package com.cliniterra.controller;

import com.cliniterra.model.DoctorModel;
import com.cliniterra.service.FindDocService;
import com.cliniterra.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet(urlPatterns = { "/addDoc" })
public class AddController extends HttpServlet {
    private final FindDocService service = new FindDocService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<DoctorModel> doctors = service.getAllDoctors();
            req.setAttribute("doctors", doctors);
            req.getRequestDispatcher("/WEB-INF/pages/addDoctor.jsp")
               .forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Unable to load doctors", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // gather + validate…
        String fn     = req.getParameter("docFirstName"),
               ln     = req.getParameter("docLastName"),
               g      = req.getParameter("docGender"),
               em     = req.getParameter("docEmail"),
               ph     = req.getParameter("docPhone"),
               dobStr = req.getParameter("docDob"),
               sp     = req.getParameter("docSpeciality"),
               ad     = req.getParameter("docAddress");

        LocalDate dob = null;
        try {
            dob = LocalDate.parse(dobStr);
        } catch (DateTimeParseException x) {
            req.setAttribute("error", "Invalid date of birth format.");
            // re-display form with error
            doGet(req, resp);
            return;
        }

        DoctorModel doc = new DoctorModel(fn, ln, g, em, ph, dob, sp, ad);

        try {
            boolean ok = service.addDoctor(doc);
            if (!ok) {
                req.setAttribute("error", "Add failed for unknown reasons.");
                doGet(req, resp);
                return;
            }
            // successful insert → redirect to clear POST
            resp.sendRedirect(req.getContextPath() + "/addDoc");
        }
        catch (SQLIntegrityConstraintViolationException dupEx) {
            // duplicate-speciality key violation
            req.setAttribute("error", "That specialty already exists.");
            // forward back to form; doGet will repopulate the doctors list
            doGet(req, resp);
        }
        catch (SQLException e) {
            // any other SQL error
            throw new ServletException("DB error adding doctor", e);
        }
    }
}
