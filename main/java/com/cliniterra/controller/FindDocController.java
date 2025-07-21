package com.cliniterra.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.cliniterra.model.DoctorModel;
import com.cliniterra.service.FindDocService;

/**
 * @author Aayush Shrestha
 */
/**
 * Servlet implementation class FindDocController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/finddoctor" })
public class FindDocController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // now using FindDocService
    private final FindDocService findDocService = new FindDocService();

    public FindDocController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1) fetch all doctors
            List<DoctorModel> doctors = findDocService.getAllDoctors();
            // 2) put into REQUEST scope under "doctors"
            request.setAttribute("doctors", doctors);
            // 3) forward to JSP
            request.getRequestDispatcher("/WEB-INF/pages/finddoctor.jsp")
                   .forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error loading doctors", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
