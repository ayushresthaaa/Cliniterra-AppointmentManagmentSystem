<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Clinic Appointment</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/book.css" />
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

  <!-- Main -->
  <div class="container">
    <div class="left-panel">
      <h2>Welcome to</h2>
      <h1>Cliniterra</h1>
      <img src="${pageContext.request.contextPath}/resources/images/adamdivaz.jpg" alt="Doctor" />
    </div>

    <div class="right-panel">
      <h2>Book a Clinic Appointment</h2>
      <p>Doctor and time slot are selected for you.</p>
      <!-- Booking Messages -->
      <c:if test="${not empty bookingError}">
        <div style="color: red; font-weight: bold; margin-bottom: 10px;">
          ${bookingError}
        </div>
      </c:if>
      <c:if test="${bookingSuccess}">
        <div style="color: green; font-weight: bold; margin-bottom: 10px;">
          Booking successful!
        </div>
      </c:if>

      <form id="appointmentForm" action="${pageContext.request.contextPath}/book" method="post">

        <!-- Removed User Selection Dropdown -->

        <!-- Doctor info shown as read-only -->
        <label>Doctor:</label>
        <input type="text" value="Dr. ${doctor.docFirstName} ${doctor.docLastName} (${doctor.docSpeciality})" readonly />
        <input type="hidden" name="doc_id" value="${doctor.docId}" />

        <!-- Schedule selection (only slots for this doctor) -->
        <label for="schedule">Select Schedule<span>*</span></label>
        <select id="schedule" name="schedule_id" required>
          <option value="">-- Choose a Time Slot --</option>
          <c:forEach var="schedule" items="${schedules}">
            <option value="${schedule.schedule_id}">
              ${schedule.schedule_day}: ${schedule.start_time} - ${schedule.end_time}
            </option>
          </c:forEach>
        </select>

        <label for="date">Appointment Date<span>*</span></label>
        <input type="date" id="date" name="appointment_date" required />

        <button type="submit">Book Appointment</button>
      </form>
    </div>
  </div>

  <script src="book.js"></script>

  <!-- Footer section -->
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
