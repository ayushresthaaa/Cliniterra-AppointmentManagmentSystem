package com.cliniterra.controller;

import com.cliniterra.model.DoctorModel;
import com.cliniterra.model.DoctorViewScheduleModel;
import com.cliniterra.service.ViewDoctorService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/viewdoctor" })
public class ViewDocController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ViewDoctorService viewDoctorService;

    public ViewDocController() {
        super();
        viewDoctorService = new ViewDoctorService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String docIdParam = request.getParameter("doc_id");
        DoctorModel doctor = null;
        List<DoctorViewScheduleModel> scheduleList = null;

        if (docIdParam != null) {
            try {
                int docId = Integer.parseInt(docIdParam);
                doctor = viewDoctorService.getDoctorById(docId);
                scheduleList = viewDoctorService.getScheduleByDoctorId(docId); // fetch schedule list
            } catch (NumberFormatException e) {
                // handle invalid doc_id
            }
        }

        if (doctor != null) {
            request.setAttribute("doctor", doctor);
            request.setAttribute("scheduleList", scheduleList); // add schedule list to request
            request.getRequestDispatcher("WEB-INF/pages/viewdoctor.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/finddoctor");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
