package com.cliniterra.controller;

/**
 * @author Aayush Shrestha
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.cliniterra.util.CookiesUtil;
import com.cliniterra.util.SessionUtil;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CookiesUtil.delete(res, "role");
		SessionUtil.destroySes(req);
		res.sendRedirect(req.getContextPath() + "/login");
	}

}
