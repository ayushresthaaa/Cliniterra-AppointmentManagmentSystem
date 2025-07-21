<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--@author Aayush Shrestha-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device=width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	    <div class="navbar"> 
    <img class="logo" src="${pageContext.request.contextPath}/resources/images/clinilogobgrem.png" alt="Logo" width="20px" height="20px">
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
    <div class="login-section">
        
        <div class="login-container">
        	
            
            <form method="post" class="login-form">
                <h1>Log In <br> To Cliniterra</h1>
                <!-- Display error message if available -->
			
                <input type = "text" name="username" class="login-box" placeholder="Enter Username">
                <input type="password" name="password" class="login-box" placeholder="Enter Password">
                <div class="error-message">
                	 <c:if test="${not empty error}">
							<p class="error-message">${error}</p>
						</c:if>
				
						<!-- Display success message if available -->
						<c:if test="${not empty success}">
							<p class="success-message">${success}</p>
						</c:if>
                </div>
               
                <input type="submit" value="Login" id="login-submit">
                <a id = "register" href="${pageContext.request.contextPath}/registration">Register your account here</a>
            </form>
            <div class="login-side">
                <!-- <h2>Appoint your preferred</h2>
                <h2>Doctor in One Click</h2> -->
                <img src="${pageContext.request.contextPath}/resources/images/logindoc.png" alt="picture of doctor">
            </div>
        </div>
    </div>
</body>
</html>