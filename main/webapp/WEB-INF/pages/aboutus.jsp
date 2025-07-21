<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!--@author Aashika Kambang-->   
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>About Us - Clinic Appointment System</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutUs.css">
</head>
<body>

  <header>
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
  
   
  </header>

  <h1>About Us</h1>

<!--Main-->
  <div class="container">
    <div class="container2">
    <section class="text-section">
    
    <h2>Book your clinic appointment<br> with an ease</h2>
    <p> A feature-rich and comprehensive medical appointment <br>scheduling software solution to deliver a fast and reliable <br>appointment booking experience to patients.</p>
    <p class="dot"><span style="color:red; font-size: 23px; border-radius: 50%; font-weight:bold">&#183 </span>Emergency Help   
    <span style="color:red; font-size: 23px; border-radius: 50%; font-weight:bold">&#183 </span> Qualified Doctors
    <span style="color:red; font-size: 23px; border-radius: 50%; font-weight:bold">&#183 </span> Best Professional  
    <span style="color:red; font-size: 23px; border-radius: 50%; font-weight:bold">&#183 </span> Medical Treatment</p>
    <div class="button-container">
    <a href="#" class="contact-button">Contact Us</a>
   
   </div>
    </section> 
     <section class="image-section">
     <img src="${pageContext.request.contextPath}/resources/images/adamdivaz.jpg" alt="Description of first image">
     <img src="${pageContext.request.contextPath}/resources/images/dc2.jpg" alt="Description of second image">
     <img src="${pageContext.request.contextPath}/resources/images/dc3.jpg" alt="Description of third image">
     <img src="${pageContext.request.contextPath}/resources/images/dc4.jpg" alt="Description of fourth image">
    </section> 
    </div>
    <h5>Our Doctors</h5>
    <h6> Meet the Best doctors</h6>

    <div class="features">
  <div class="feature">
    <div class="doctor-profile">
      <img src="${pageContext.request.contextPath}/resources/images/adamdivaz.jpg" alt="Emergency Help Doctor">
      <div class="doctor-info">
        <h4>Adam Diaz</h4>
        <p>Marine Medicine</p>
      </div>
    </div>
    <h3>Emergency Help</h3>
    <a href="book.html" class="appointment-btn">Book An Appointment</a>

  </div>
  
  <div class="feature">
    <div class="doctor-profile">
      <img src="${pageContext.request.contextPath}/resources/images/kimsam.jpg" alt="Qualified Doctor">
      <div class="doctor-info">
        <h4>Kim Sam</h4>
        <p>Cardiology</p>
      </div>
    </div>
    <h3>Qualified Doctors</h3>
    <a href="book.html" class="appointment-btn">Book An Appointment</a>

  </div>
    <div class="feature">
    <div class="doctor-profile">
      <img src="${pageContext.request.contextPath}/resources/images/adamsmith.jpg" alt="Qualified Doctor">
      <div class="doctor-info">
        <h4>Adam Smith </h4>
        <p>General Physian</p>
      </div>
    </div>
    <h3>Qualified Doctors</h3>
    <a href="book.html" class="appointment-btn">Book An Appointment</a>

  </div>
    <div class="feature">
    <div class="doctor-profile">
      <img src="${pageContext.request.contextPath}/resources/images/samara.jpg" alt="Qualified Doctor">
      <div class="doctor-info">
        <h4>Samara Ford</h4>
        <p>Gynologist</p>
      </div>
    </div>
    <h3>Qualified Doctors</h3>
    <a href="book.html" class="appointment-btn">Book An Appointment</a>

  </div>
      <div class="feature">
    <div class="doctor-profile">
      <img src="${pageContext.request.contextPath}/resources/images/sanduik.jpg" alt="Qualified Doctor">
      <div class="doctor-info">
        <h4>Sanduk Ruit</h4>
        <p>Opthalmologist</p>
      </div>
    </div>
    <h3>Qualified Doctors</h3>
    <a href="book.html" class="appointment-btn">Book An Appointment</a>

  </div>

 
  <!-- Repeat for other features -->
</div>
  </div>
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
