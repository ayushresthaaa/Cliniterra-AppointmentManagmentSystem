<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--@author Aayush Shrestha-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/registrationform.css" />
</head>

<body>
        <div class="navbar"> 
    <img class="logo" src="${pageContext.request.contextPath}/resources/images/clinilogobgrem.png" alt="Logo">
    <div class="nav-links">
      <a href="${pageContext.request.contextPath}/home">Home</a>
      <a href="${pageContext.request.contextPath}/finddoctor">Find doctor</a>
      <a href="${pageContext.request.contextPath}/view">View appointment</a>
      <a href="${pageContext.request.contextPath}/aboutus">About us</a>
      <a href="${pageContext.request.contextPath}/contact">Contact</a>
    
      <!--<a href="#" class="button">Book now</a>-->
     </div>
		<a href="${pageContext.request.contextPath}/book" class="button">Book now</a>
  </div>
    <div class="section">
        <h1>Register <br> Your Account</h1>
	        <div class="container">
	        	
            <form action="${pageContext.request.contextPath}/registration"
                method="post" enctype="multipart/form-data">
                <div class="row">
                    <div class="col">
                        <!-- <label for="firstName">First Name:</label> -->
                        <input type="text" id="firstName" placeholder="Enter Your Firstname" name="firstName" required>
                    </div>
                    <div class="col">
                        <!-- <label for="lastName">Last Name:</label> -->
                        <input type="text" id="lastName" placeholder="Enter Your Lastname" name="lastName" required>
                    </div>
                    <div class="col">
                        <!-- <label for="username">Username:</label> -->
                        <input type="text" id="username" placeholder="Select Username" name="username" required>
                    </div>
                </div>
                <div class="col" id="hello">
                    <!-- <div class="col"> -->
                        <label for="birthday">Date of Birth:</label>
                        <input type="date" id="birthday"  name="dob" required>
                </div>
                <div class="row">
                    
                    <div class="col">
                        <!-- <label for="email">Email:</label> -->
                        <input type="email" id="email" placeholder="Enter Email" name="email" required>
                    </div>
                </div>
                 <div class="row">
                    
                    <div class="col">
                        <!-- <label for="email">Email:</label> -->
                        <input type="text" id="phone" placeholder="Enter Phone number" name="number" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="gender">Gender:</label> <select id="gender"
                            name="gender" required>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <!-- <label for="password">Password:</label> -->
                        <input type="password" id="password" placeholder="Write Password" name="password" required>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col">
                        <!-- <label for="retypePassword">Retype Password:</label> -->
                        <input type="password" id="retypePassword" placeholder="Retype Your Password" name="retypePassword" required>
                        
                    </div>
                </div>
                <div class="row">
                    <div class="col" id="hello">
                        <label for="profile">Profile Picture:</label> 
                        <input type="file" id="image" name="image"  placeholder="">
                    </div>
                </div>
				<div class = "row">
					<div class= "col">
									<!-- Display error message if available -->
						<c:if test="${not empty error}">
							<p class="error-message">${error}</p>
						</c:if>
				
						<!-- Display success message if available -->
						<c:if test="${not empty success}">
							<p class="success-message">${success}</p>
						</c:if>
					</div>
				</div>
                <!-- Buttons Row -->
                <div class="row buttons-row">
                    <a href="${pageContext.request.contextPath}/login" class="back-button">Back</a>
    				<button type="submit" class="submit-button">Submit</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>