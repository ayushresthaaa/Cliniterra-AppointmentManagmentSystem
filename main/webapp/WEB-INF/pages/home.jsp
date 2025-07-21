<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--@author Aayusha Kandel-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="currentUser" value="${sessionScope.username}" />    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Cliniterra</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
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

      <c:choose>
        <c:when test="${not empty currentUser}">
          <!-- Profile link shown only when logged in -->
          <a href="${pageContext.request.contextPath}/viewprofile">Profile</a>
          <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:when>
        <c:otherwise>
          <a href="${pageContext.request.contextPath}/login">Login</a>
        </c:otherwise>
      </c:choose>
    </div>
    <a href="${pageContext.request.contextPath}/book" class="button">Book now</a>
  </div>

  <!-- Main Section -->
  <div class="main">
    <div class="bg-overlay"></div>
    <div class="main-left">
      <h1>Cliniterra</h1>
      <p>
        Smart health care scheduling and patient care. We connect you with the right care, at the right time, all in just a few clicks.
      </p>
      <a href="${pageContext.request.contextPath}/finddoctor"><button>Appointment</button></a>
      <button>📞 01-5423467</button>
    </div>
    <div class="main-right">
      <img src="${pageContext.request.contextPath}/resources/images/National Hospital Week 2022_ We Are Health Care.jpeg" alt="DNA Image">
    </div>
  </div>

  <div class="doctor-card">
    <img src="${pageContext.request.contextPath}/resources/images/doc.jpeg" alt="Doctor">
    <img src="${pageContext.request.contextPath}/resources/images/doc1.jpeg" alt="Doctor1">
    <img src="${pageContext.request.contextPath}/resources/images/doctor handsome.jpeg" alt="Doctor2">
    <img src="${pageContext.request.contextPath}/resources/images/doc3.jpeg" alt="Doctor3">
    <div class="info">
      <p>Consult with your specific doctor</p>
      <a href="findDoctor.html"><button>Our doctors</button></a>
    </div>
  </div>

  <!-- opening time-->
  <div class="time">
    <h1>Opening Hours</h1>
    <p>Visit our clinic</p>
    <div class="time-table">
      Sunday - 8:00 AM : 6:00 PM<br/>
      Monday - 8:00 AM : 6:00 PM<br/>
      Tuesday - 8:00 AM : 6:00 PM<br/>
      Wednesday - 8:00 AM : 6:00 PM<br/>
      Thursday - 8:00 AM : 6:00 PM<br/>
      Friday - 8:00 AM : 6:00 PM<br/>
      Saturday - 10:00 AM : 5:00 PM
    </div>
  </div>

  <!-- Second section -->
  <section class="section">
    <div class="top-bar">
      <h1>🏥 Our Department</h1>
    </div>
    <h2>Health Solutions</h2>
    <p class="desc">Cliniterra includes specialized pediatric, dermatological, orthopedic, cardiology, and dental care sections,
      all of which are staffed by highly qualified specialists and equipped with advanced technology.</p>
    <div class="cards">
      <div class="card">
        <div class="card-icon">01</div>
        <h3>Marine Medicine<br>Sector</h3>
        <p>A specialized field focusing on the health and medical care of seafarers and maritime workers, 
          often addressing conditions related to marine environments.</p>         
      </div>
      <div class="card">
        <div class="card-icon">02</div>
        <h3>Neurologist<br>Sector</h3>
        <p>A doctor who diagnoses and treats disorders of the nervous system, including the brain, spinal cord, and nerves.</p>   
      </div>
      <div class="card">
        <div class="card-icon">03</div>
        <h3>Gynologist<br>Sector</h3>
        <p>A medical specialist in women's reproductive health, including the diagnosis and treatment of 
          issues related to the uterus, ovaries, and hormonal cycles.</p>
      </div>
      <div class="card">
        <div class="card-icon">04</div>
        <h3>Cardiology<br>Sector</h3>
        <p>Focuses on treating heart-related conditions such as hypertension, heart attacks, and arrhythmias.</p>
      </div>
      <div class="card">
        <div class="card-icon">05</div>
        <h3>Opthalmologist<br>Sector</h3>
        <p>A physician who specializes in eye and vision care, including performing eye exams, 
          prescribing glasses, and conducting eye surgeries.</p>
      </div>
    </div>
  </section>

  <!-- third section -->
  <section class="doctor-availablity">
    <div class="text">
      <h2>Our Services</h2>
      <p>Our goal at Cliniterra is to provide our community with skillful care, compassion, and dedication.
        <br> From general consultations to specialized treatments, our expert team ensures you receive 
        the best possible medical support.
      </p>
    </div>
    <div class="banner-image">
      <img src="${pageContext.request.contextPath}/resources/images/group of doc.png" alt="Doctors">
    </div>
  </section>

  <!-- fourth section -->
  <section class="Working">
    <h2>How It Works!</h2>
    <p>
      Discover, book, and experience personalized healthcare effortlessly<br />
      with our user-friendly Doctor Appointment Website.
    </p>
    <div class="steps">
      <div class="step">
        <div class="iconC">🩺</div>
        <h3>Find A Doctor</h3>
        <p>Discover skilled doctors based on specialization and location.</p>
      </div>
      <div class="step">
        <div class="iconC">📆</div>
        <h3>Book Appointment</h3>
        <p>Effortlessly book appointments at your convenience.</p>
      </div>
      <div class="step">
        <div class="iconC">🏥</div>
        <h3>Get Services</h3>
        <p>Receive personalized healthcare services tailored to your needs.</p>
      </div>
    </div>
  </section>

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
