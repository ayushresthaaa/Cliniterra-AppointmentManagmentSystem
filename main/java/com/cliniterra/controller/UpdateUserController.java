package com.cliniterra.controller;

/**
 * @author Aayush Shrestha
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.cliniterra.model.UserModel;
import com.cliniterra.service.UpdateUserService;
import com.cliniterra.util.ImageUtil;
import com.cliniterra.util.ValidationUtil;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updateuser" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB

public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private final ImageUtil imgobj = new ImageUtil(); 
	private final UpdateUserService updateUser = new UpdateUserService(); 
//	private final ValidationUtil validationUtil = new ValidationUtil();
//	private final RedirectUtil redirect = new RedirectUtil();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/pages/VUprofile.jsp").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	   try {
               // Validate input
               String validMsg = validateFields(req);
               if (validMsg != null) {
                   handleError(req, res, validMsg);
                   return;
               }

               // Extract updated user info
               UserModel user = extractUser(req);
               // Identify record by username in session
               String username = (String) req.getSession().getAttribute("username");
               user.setUserName(username);

               // Perform update
               boolean updated = updateUser.updateUser(user);
               if (updated) {
                   handleSuccess(req, res, "Profile updated successfully!", "/WEB-INF/pages/profile.jsp");
               } else {
                   handleError(req, res, "Could not update profile. Please try again later!");
               }
           } catch (Exception e) {
               handleError(req, res, "An unexpected error occurred. Please try again later!");
               e.printStackTrace();
           }
       }

       private String validateFields(HttpServletRequest req) throws IOException, ServletException {
          
           if (ValidationUtil.isNullempty(req.getParameter("firstName"))) 
        	   return "First name is required.";
           if (ValidationUtil.isNullempty(req.getParameter("lastName"))) 
        	   return "Last name is required.";
           if (ValidationUtil.isNullempty(req.getParameter("dob")))       
        	   return "Date of birth is required.";
           if (ValidationUtil.isNullempty(req.getParameter("gender")))   
        	   return "Gender is required.";
           if (ValidationUtil.isNullempty(req.getParameter("email")))    
        	   return "Email is required.";
           if (ValidationUtil.isNullempty(req.getParameter("number")))   
        	   return "Phone number is required.";

           // Parse and validate date of brith
           LocalDate dob;
           try {
               dob = LocalDate.parse(req.getParameter("dob"));
           } catch (Exception e) {
               return "Invalid date format. Please use YYYY-MM-DD.";
           }
           if (!ValidationUtil.isAgeAtLeast12(dob))
               return "You must be at least 12 years old to update.";

           // Additional checks
           if (!ValidationUtil.isValidGender(req.getParameter("gender")))
               return "Gender must be 'male' or 'female'.";
           if (!ValidationUtil.isValidEmail(req.getParameter("email")))
               return "Invalid email format.";
           if (!ValidationUtil.isValidPhoneNumber(req.getParameter("number")))
               return "Phone number must be 10 digits and start with 98.";

           //  image validation
           Part img = req.getPart("image");
           if (img != null && img.getSize() > 0 && !ValidationUtil.isValidImageExtension(img))
               return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";

           return null;
       }

       private UserModel extractUser(HttpServletRequest req) throws IOException, ServletException {
           UserModel user = new UserModel();
           
           user.setFirstName(req.getParameter("firstName"));
           user.setLastName(req.getParameter("lastName"));
           user.setDob(LocalDate.parse(req.getParameter("dob")));
           user.setGender(req.getParameter("gender"));
           user.setEmail(req.getParameter("email"));
           user.setNumber(req.getParameter("number"));

           // image handling 
           Part image = req.getPart("image");
           if (image != null && image.getSize() > 0) {
               String imgUrl = imgobj.getImageName(image);
               imgobj.uploadImage(image, req.getServletContext().getRealPath("/"), "users");
               user.setImageUrl(imgUrl);
           }
           return user;
       }

       private void handleSuccess(HttpServletRequest req, HttpServletResponse res, String mesg, String page)
               throws ServletException, IOException {
           req.setAttribute("success", mesg);
           req.getRequestDispatcher(page).forward(req, res);
       }

       private void handleError(HttpServletRequest req, HttpServletResponse res, String err)
               throws ServletException, IOException {
           req.setAttribute("error", err);
           req.getRequestDispatcher("/WEB-INF/pages/VUprofiles.jsp").forward(req, res);
       }
}

