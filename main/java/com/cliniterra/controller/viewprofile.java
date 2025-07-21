package com.cliniterra.controller;

import com.cliniterra.model.UserModel;
import com.cliniterra.service.ViewProfileService;
import com.cliniterra.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/viewprofile" })
public class viewprofile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ViewProfileService vps;

    @Override
    public void init() throws ServletException {
        vps = new ViewProfileService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = SessionUtil.getUsername(request);

        if (username != null) {
            UserModel user = vps.getUserByUsername(username);
            if (user != null) {
                request.setAttribute("user", user);

                // Determine JSP based on user type
                String jspPage = isAdmin(user)
                        ? "/WEB-INF/pages/adminprofile.jsp"
                        : "/WEB-INF/pages/VUprofile.jsp";

                request.getRequestDispatcher(jspPage).forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = SessionUtil.getUsername(request);

        if (username != null) {
            UserModel user = new UserModel();
            user.setUserName(username);
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setNumber(request.getParameter("phone"));
            user.setEmail(request.getParameter("email"));

            String dobStr = request.getParameter("dob");
            if (dobStr != null && !dobStr.isEmpty()) {
                user.setDob(java.time.LocalDate.parse(dobStr));
            }

            boolean success = vps.updateUser(user);
            if (success) {
                request.setAttribute("updateSuccess", true);
            } else {
                request.setAttribute("updateError", "Failed to update profile.");
            }

            UserModel updatedUser = vps.getUserByUsername(username);
            request.setAttribute("user", updatedUser);

            String jspPage = isAdmin(updatedUser)
                    ? "/WEB-INF/pages/adminprofile.jsp"
                    : "/WEB-INF/pages/VUprofile.jsp";

            request.getRequestDispatcher(jspPage).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    private boolean isAdmin(UserModel user) {
        return "admin".equalsIgnoreCase(user.getUserName());
        // Or use role: return "admin".equalsIgnoreCase(user.getRole());
    }
}
