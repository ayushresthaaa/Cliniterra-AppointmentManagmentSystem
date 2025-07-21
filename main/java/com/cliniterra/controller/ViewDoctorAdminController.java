package com.cliniterra.controller;

import com.cliniterra.model.DoctorModel;
import com.cliniterra.service.DoctorServiceAdmin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/viewdoctorsadmin" })
public class ViewDoctorAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Renamed to DoctorServiceAdmin
    private final DoctorServiceAdmin doctorService = new DoctorServiceAdmin();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // fetch all doctors
        List<DoctorModel> doctors = doctorService.getAllDoctors();
        request.setAttribute("doctors", doctors);

        // forward to JSP
        request.getRequestDispatcher("WEB-INF/pages/viewdoctorsadmin.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // no POST behavior needed for view page
        doGet(request, response);
    }
}
