<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Find a Doctor - Clinic Appointment System</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/finddoctor.css">
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
    </div>
    <a href="${pageContext.request.contextPath}/book" class="button">Book now</a>
  </div>
  <div class="container">
    <img src="${pageContext.request.contextPath}/resources/images/image.png" class="full-screen-width banner-img">
    <h1> MAKE A DOCTOR'S APPOINTMENT</h1>
    <div class="features">
      <c:forEach var="doctor" items="${doctors}">
        <div class="feature">
          <div class="doctor-profile">
            <div class="doctor-info">
              <h4>${doctor.docFirstName} ${doctor.docLastName}</h4>
              <p>${doctor.docSpeciality}</p>
            </div>
          </div>
          <h3>${doctor.docAddress}</h3> <!-- or use ward if available -->
          <div class="button-container">
            <a href="${pageContext.request.contextPath}/book?doc_id=${doctor.docId}" class="button">Book now</a>
            <a href="${pageContext.request.contextPath}/viewdoctor?doc_id=${doctor.docId}" class="View-btn">View Profile</a>

          </div>
        </div>
      </c:forEach>
    </div>
  </div>
   
  <footer class="footer">
    <div class="footer-container">
      <div class="footer-section">
        <h3>Contact Info</h3>
        <p>📞 01-5423467, +977-9845786430, +977-9267453897</p>
        <p>📨 cliniterra01@gmail.com</p>
        <p>📍Kamalpokhari, Kathmandu, Nepal</p>
      </div>
      <div class="footer-section">
        <h3>Services</h3>
        <div class="departments">
          <ul>
            <li>OPD Ward</li>
            <li>Neurologist</li>
            <li>Gynologist</li>
            <li>Cardiology</li>
            <li>Opthalmologist</li>
          </ul>
        </div>
      </div>
      <div class="footer-section">
        <h3>Support</h3>
        <div class="foo-column">
          <ul>
            <li><a href="#">Book Appointment</a></li>
            <li><a href="#">Our doctors</a></li>
            <li><a href="#">Contact Us</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-bottom">
      &copy; 2025 Cliniterra. All rights reserved.
    </div>
  </footer>
</body>
</html>
