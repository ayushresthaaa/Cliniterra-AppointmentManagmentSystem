<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--@author Namrata Karki -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Patient Profile</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/VUprofile.css">
</head>
<body>

   

  <!-- Sidebar -->
  <!-- <div class="sidebar">
    <a href="#">Dashboard</a>
    <a href="editsche.html">Schedules</a>
    <a href="#">Doctors</a>
    <a href="view_user.html">Patients</a>
    <a href="addDoc.html">Add Doctors</a>
    <a href="#">Settings</a>
  </div> -->
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
<div class="main-content">

  <!-- Search Bar -->
 <c:if test="${updateSuccess}">
   <div class="success-msg">Profile updated successfully!</div>
</c:if>
<form class="profile-card" method="post" action="${pageContext.request.contextPath}/viewprofile">
  <h2>Patient Profile</h2>

  <div class="profile-top">
    <img
  src="${pageContext.request.contextPath}/resources/images/users/${user.imageUrl}"
  onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/images/default-profile.png';"
  alt="Patient Photo"
  class="side-photo"
/>



    <div class="profile-grid">
      <div>
        <label><strong>First Name:</strong></label>
        <input type="text" name="firstName" value="${user.firstName}" />
      </div>
      <div>
        <label><strong>Last Name:</strong></label>
        <input type="text" name="lastName" value="${user.lastName}" />
      </div>
      <div>
        <label><strong>Username:</strong></label>
        <input type="text" name="username" value="${user.userName}" readonly />
      </div>
      <div>
        <label><strong>Email:</strong></label>
        <input type="email" name="email" value="${user.email}" />
      </div>
      <div>
        <label><strong>Phone:</strong></label>
        <input type="text" name="phone" value="${user.number}" />
      </div>
      <div>
        <label><strong>Date of Birth:</strong></label>
        <input type="date" name="dob" value="${user.dob}" />
      </div>
    </div>
  </div>

  <div class="action-buttons">
    <button type="submit">Save</button>
  </div>
</form>


</body>
</html>
