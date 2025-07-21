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

import com.cliniterra.util.ValidationUtil;
import com.cliniterra.model.UserModel;
import com.cliniterra.service.LoginService;
import com.cliniterra.util.CookiesUtil;
import com.cliniterra.util.RedirectUtil;
import com.cliniterra.util.SessionUtil; 
/**
 * 
 */
//this part of the servlet works as the logic for validating the username, password and redirecting the user based on session and cookies
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private ValidationUtil validate; 
//	private RedirectUtil redirect; 
	private LoginService loginService;
	
	public void init() throws ServletException{ 
//		this.validate = new ValidationUtil(); 
//		this.redirect = new RedirectUtil(); 
		this.loginService = new LoginService(); //object of LoginService
	}
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(RedirectUtil.loginurl).forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserModel user = new UserModel(username, password);
		Boolean logstat = loginService.loginUser(user); //returns null of there is no user
		
		if (Boolean.TRUE.equals(logstat)) {
			SessionUtil.setSessionAt(req, "username", username); //sets attribute of the session if the user does exists
			//if the admin is the user
			if(username.equals("Admin")) {
				CookiesUtil.addCookie(res, "role", "admin", 5*30); //sets max age of the ssion for the admin
				res.sendRedirect(req.getContextPath() + "/admindashboard");
			} else { 
				CookiesUtil.addCookie(res, "role", "user", 5*30);
				res.sendRedirect(req.getContextPath() + "/home"); //sets max age and cookie for the user 
			} 
		}
		//when login fails following code is used
		else {
			handleLoginFailure(req, res, logstat);
		}
	
	}
	//in case of failed login
	private void handleLoginFailure(HttpServletRequest req, HttpServletResponse res, Boolean logstat) throws ServletException, IOException {
		String errorMessage;
		if(logstat == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		}
		else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		req.setAttribute("error", errorMessage);
		req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, res); //redirect to login page again if the login fails
	}	
}
