// src/main/java/com/cliniterra/controller/AdminProfileController.java
package com.cliniterra.controller;

import com.cliniterra.model.AdminModel;
import com.cliniterra.service.AdminService;
import com.cliniterra.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/viewadminprofile" })
public class AdminProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = SessionUtil.getUsername(req);
        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        AdminModel admin = adminService.getAdminByUsername(username);
        if (admin == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        req.setAttribute("admin", admin);
        req.getRequestDispatcher("/WEB-INF/pages/adminprofile.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = SessionUtil.getUsername(req);
        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        AdminModel admin = new AdminModel();
        admin.setUserName(username);
        admin.setFirstName(req.getParameter("firstName"));
        admin.setLastName(req.getParameter("lastName"));
        admin.setNumber(req.getParameter("phone"));
        admin.setEmail(req.getParameter("email"));
        String dobStr = req.getParameter("dob");
        if (dobStr != null && !dobStr.isEmpty()) {
            admin.setDob(java.time.LocalDate.parse(dobStr));
        }

        boolean success = adminService.updateAdmin(admin);
        req.setAttribute("updateSuccess", success);
        if (!success) {
            req.setAttribute("updateError", "Failed to update profile.");
        }

        AdminModel updated = adminService.getAdminByUsername(username);
        req.setAttribute("admin", updated);
        req.getRequestDispatcher("/WEB-INF/pages/adminprofile.jsp")
           .forward(req, resp);
    }
}
