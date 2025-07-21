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
import com.cliniterra.service.RegistrationService;
import com.cliniterra.util.ImageUtil;
import com.cliniterra.util.PasswordUtil;
import com.cliniterra.util.ValidationUtil;

/**
 * @author Aayush Shrestha
 */
/**
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/registration" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//creation of the object of imageutil and registrationservice classes
    private final ImageUtil imgobj = new ImageUtil(); 
	private final RegistrationService register = new RegistrationService(); 
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
		req.getRequestDispatcher("WEB-INF/pages/registrationform.jsp").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    		//checks for the validity of the fields
    		String validmsg = validationReg(req);
    		if (validmsg != null) {
				handleError(req, res, validmsg);
				return;
			}
    		//create a user object by getting the info from the parameters entered
    		UserModel user = extractUser(req);
    		Boolean isadded =register.addUser(user);  //checks if the user is added or not
    		
    		//if the user is not added or is null
    		if (isadded == null) {
				handleError(req, res, "Our server is under maintenance. Please try again later!");
			} else if (isadded) { //if the user gets added
				try { //upload the image using headers.
					if (uploadImage(req)) {
						handleSuccess(req, res, "Your account is successfully created!", "/WEB-INF/pages/login.jsp");
					} else {
						handleError(req, res, "Could not upload the image. Please try again later!");
					}
				} catch (IOException | ServletException e) {
					handleError(req, res, "An error occurred while uploading the image. Please try again later!");
					e.printStackTrace(); // Log the exception
				}
			} else {
				handleError(req, res, "Could not register your account. Please try again later!");
			}
    	}
    	catch (Exception e) {
			handleError(req, res, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
    	
    } //to get the parameters from the jsp file form
    public String validationReg(HttpServletRequest req) {
    	String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("username");
		String dobStr = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String number = req.getParameter("number");
		String password = req.getParameter("password");
		String retypePassword = req.getParameter("retypePassword");
		//check for the null values
		if (ValidationUtil.isNullempty(firstName))
			return "First name is required.";
		if (ValidationUtil.isNullempty(lastName))
			return "Last name is required.";
		if (ValidationUtil.isNullempty(username))
			return "Username is required.";
		if (ValidationUtil.isNullempty(dobStr))
			return "Date of birth is required.";
		if (ValidationUtil.isNullempty(gender))
			return "Gender is required.";
		if (ValidationUtil.isNullempty(email))
			return "Email is required.";
		if (ValidationUtil.isNullempty(number))
			return "Phone number is required.";
		if (ValidationUtil.isNullempty(password))
			return "Password is required.";
		if (ValidationUtil.isNullempty(retypePassword))
			return "Please retype the password.";
		
		LocalDate dob;
		try {
			dob = LocalDate.parse(dobStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}
		
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidGender(gender))
			return "Gender must be 'male' or 'female'.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(number))
			return "Phone number must be 10 digits and start with 98.";
		if (!ValidationUtil.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";

		// Check if the date of birth is at least 16 years before today
		if (!ValidationUtil.isAgeAtLeast12(dob))
			return "You must be at least 12 years old to register.";

		try {
			Part image = req.getPart("image");
			if (!ValidationUtil.isValidImageExtension(image))
				return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
		} catch (IOException | ServletException e) {
			return "Error handling image file. Please ensure the file is valid.";
		}

		return null; // All validations passed
		
    }//upload the image into the path of the server
    public boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
    	Part image = req.getPart("image"); 
    	return imgobj.uploadImage(image, req.getServletContext().getRealPath("/"), "users");
    }//create a usermodel object user
    public UserModel extractUser(HttpServletRequest req) throws IOException, ServletException {
    	String firstName = req.getParameter("firstName"); 
    	String lastName = req.getParameter("lastName");
    	String userName = req.getParameter("username"); 
    	LocalDate dob = LocalDate.parse(req.getParameter("dob"));
    	String gender = req.getParameter("gender");
    	String email = req.getParameter("email");
    	String number = req.getParameter("number");
    	String password = req.getParameter("password"); 
    	Part image = req.getPart("image");
		String imageUrl = imgobj.getImageName(image);
		
//		password = PasswordUtil.encrypt(userName, password);
		
    	return new UserModel(firstName, lastName, userName, dob, gender, email,
    			number, password, imageUrl);
    }
    //handles if the user is successfully created
    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}
    //in case the user is not created or added to db properly
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("firstName", req.getParameter("firstName"));
		req.setAttribute("lastName", req.getParameter("lastName"));
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("dob", req.getParameter("dob"));
		req.setAttribute("gender", req.getParameter("gender"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("phoneNumber", req.getParameter("number"));
		req.getRequestDispatcher("/WEB-INF/pages/registrationform.jsp").forward(req, resp);
	}
}

