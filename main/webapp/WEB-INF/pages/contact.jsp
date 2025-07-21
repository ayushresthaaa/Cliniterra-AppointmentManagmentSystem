<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--@author Aayusha Kandel-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact.css">
</head>
<body>

   <!-- Navbar -->
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
    


  <!-- First section -->
<div class="container">
  <h1>Get In Touch with us</h1>
  <p>We prioritize your health first</p>
  <div class="contact-wrapper">
    
    <div class="contact-info">
      <h3>Contact us for further Information</h3>
      <div>📞 +977-9845786430</div>
      <div>📞 +977-9267453897</div>
      <div>📨 cliniterra01@gmail.com</div>
      <div>📍 KamalPokhari, Kathmandu</div>
    </div>

    <form class="contact-form" method="post" action="${pageContext.request.contextPath}/contact">
      <textarea name="feedbackDesc" placeholder="Write your feedback here" required></textarea>
      <input type="date" name="feedbackDate" required />
      <button class="send-btn" type="submit">Send Feedback</button>
    </form>
  </div>
</div>

<!-- Footer section -->
<footer class="footer">
  <div class="footer-container">
    <!-- Contact Info -->
    <div class="footer-section">
      <h3>Contact Info</h3>
      <p>📞 01-5423467, +977-9845786430, +977-9267453897</p>
      <p>📨 cliniterra01@gmail.com</p>
      <p>📍Kamalpokhari, Kathmandu, Nepal</p>
    </div>

    <!-- Departments -->
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

    <!-- Support -->
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
