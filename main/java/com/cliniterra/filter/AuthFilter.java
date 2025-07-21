package com.cliniterra.filter;

import java.io.IOException;
import com.cliniterra.util.CookiesUtil;
import com.cliniterra.util.SessionUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class AuthFilter implements Filter {
    private static final String Login          = "/login";
    private static final String Register       = "/registration";
    private static final String Home           = "/home";
    private static final String Root           = "/";
    private static final String FindDoc        = "finddoctor";
    private static final String Contact        = "contact";
    private static final String Aboutus        = "aboutus";
    private static final String Viewprofile    = "viewprofile";
    private static final String Viewdoctor     = "viewdoctor";
    private static final String Book           = "book";
    private static final String Viewappointment= "view";
    private static final String Dashboard      = "/admindashboard";
    private static final String AddDoc         = "/addDoc";
    private static final String RemoveDoctor   = "/removeDoctor";   // new
    private static final String AdminProfile   = "/viewadminprofile";
    private static final String EditSchedule   = "/editSchedule";    // lowercase to match mapping
    private static final String Viewdocadmin   = "/viewdoctorsadmin";   
    @Override
    public void init(FilterConfig config) throws ServletException {
        Filter.super.init(config);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain )
            throws IOException, ServletException {
        HttpServletRequest  req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        // allow static resources
        if (uri.endsWith(".css") || uri.endsWith(".jpg") || uri.endsWith(".png") || uri.endsWith(".jpeg")) {
            chain.doFilter(req, res);
            return;
        }

        boolean loggedIn = SessionUtil.getAt(req, "username") != null;
        String role = CookiesUtil.get(req, "role") != null
                ? CookiesUtil.get(req, "role").getValue()
                : null;

        if ("admin".equals(role)) {
            // Admin allowed URLs
            if (uri.endsWith(Login) || uri.endsWith(Register)) {
                res.sendRedirect(req.getContextPath() + Dashboard);
            }
            else if (
                   uri.endsWith(Dashboard)
                || uri.endsWith(AddDoc)
                || uri.endsWith(RemoveDoctor)    // now allowed
                || uri.endsWith(AdminProfile)
                || uri.endsWith(EditSchedule)
                || uri.endsWith(Viewdocadmin)
                || uri.endsWith("logout")
            ) {
                chain.doFilter(req, res);
            } else {
                res.sendRedirect(req.getContextPath() + Dashboard);
            }
        }
        else if ("user".equals(role)) {
            // User allowed URLs
            if (uri.endsWith(Login) || uri.endsWith(Register)) {
                res.sendRedirect(req.getContextPath() + Home);
            }
            else if (
                   uri.endsWith(Home)
                || uri.endsWith(FindDoc)
                || uri.endsWith(Viewdoctor)
                || uri.endsWith(Aboutus)
                || uri.endsWith(Contact)
                || uri.endsWith(Book)
                || uri.endsWith(Viewprofile)
                || uri.endsWith(Viewappointment)
                || uri.endsWith("logout")
            ) {
                chain.doFilter(req, res);
            } else {
                res.sendRedirect(req.getContextPath() + Home);
            }
        }
        else {
            // Unauthenticated allowed URLs
            if (
                   uri.endsWith(Login)
                || uri.endsWith(Register)
                || uri.endsWith(Root)
                || uri.endsWith(Home)
                || uri.endsWith(Aboutus)
                || uri.endsWith(Contact)
            ) {
                chain.doFilter(req, res);
            } else {
                res.sendRedirect(req.getContextPath() + Login);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
