<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>My Upcoming Appointments</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/viewappointment.css" />
</head>
<body>

  <!-- Navbar -->
  <div class="navbar"> 
    <img class="logo" src="${pageContext.request.contextPath}/resources/images/clinilogobgrem.png" alt="Logo" />
    <div class="nav-links">
      <a href="${pageContext.request.contextPath}/home">Home</a>
      <a href="${pageContext.request.contextPath}/finddoctor">Find doctor</a>
      <a href="${pageContext.request.contextPath}/view">View appointment</a>
      <a href="${pageContext.request.contextPath}/aboutus">About us</a>
      <a href="${pageContext.request.contextPath}/contact">Contact</a>
    </div>
    <a href="${pageContext.request.contextPath}/book" class="button">Book now</a>
  </div>

  <!-- Main Content -->
  <div class="container">
    <h2>My Upcoming Appointments</h2>

    <c:if test="${not empty error}">
      <div style="color: red; font-weight: bold; margin-bottom: 20px;">
        ${error}
      </div>
    </c:if>

    <div class="appointments-section">
      <c:choose>
        <c:when test="${not empty appointments}">
          <c:forEach var="appointment" items="${appointments}">
            <div class="appointment-card">
              <div class="appointment-header">
                <div>
                  <div class="date">
                    <fmt:formatDate value="${appointment.appointmentDate}" pattern="dd" />
                  </div>
                  <div class="day">
                    <fmt:formatDate value="${appointment.appointmentDate}" pattern="EEE" />
                  </div>
                </div>
                <div class="appointment-details">
                  <div><strong>Date:</strong> <fmt:formatDate value="${appointment.appointmentDate}" pattern="yyyy-MM-dd" /></div>
                  <div><strong>Schedule:</strong> ${appointment.scheduleDay}: ${appointment.startTime} - ${appointment.endTime}</div>
                  <div><strong>With:</strong> ${appointment.doctorName}</div>
                </div>
              </div>
            </div>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <p>No upcoming appointments found.</p>
        </c:otherwise>
      </c:choose>
    </div>

    <button class="book-btn" onclick="location.href='${pageContext.request.contextPath}/book'">Book new Appointment</button>
  </div>

  <!-- Footer -->
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
        <ul>
          <li>Orthopedic</li>
          <li>Dermatology</li>
          <li>Pediatric</li>
          <li>Cardiology</li>
          <li>Dentistry</li>
        </ul>
      </div>
      <div class="footer-section">
        <h3>Support</h3>
        <ul>
          <li><a href="#">Book Appointment</a></li>
          <li><a href="#">Our doctors</a></li>
          <li><a href="#">Contact Us</a></li>
        </ul>
      </div>
    </div>
    <div class="footer-bottom">
      &copy; 2025 Cliniterra. All rights reserved.
    </div>
  </footer>
</body>
</html>
