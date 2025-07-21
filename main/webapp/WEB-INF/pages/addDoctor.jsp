<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Cliniterra – Add/Remove Doctor</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addDoctor.css">
</head>
<body>

  <!-- Admin Sidebar -->
  <div class="sidebar1">
    <a href="${pageContext.request.contextPath}/admindashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/admindashboard">Schedules</a>
    <a href="${pageContext.request.contextPath}/admindashboard">Doctors</a>
    <a href="${pageContext.request.contextPath}/admindashboard">Patients</a>
    <a href="${pageContext.request.contextPath}/admindashboard">Add Doctors</a>
    <a href="${pageContext.request.contextPath}/admindashboard">Settings</a>
  </div>

  <!-- Main Content: two-column grid via .add-doctor-page -->
  <main class="add-doctor-page">

    <!-- Invisible placeholder keeps left column width but shows nothing -->
    <aside class="sidebar">
      <div style="visibility:hidden; height:100%; width:100%;"></div>
    </aside>

    <!-- Right: Forms -->
    <section class="form-section">

      <!-- Add New Doctor -->
      <h2>Add New Doctor</h2>
      <form class="doctor-form" method="post" action="${pageContext.request.contextPath}/addDoc">
        <div class="form-row">
          <div class="form-group">
            <label for="firstName">First Name *</label>
            <input id="firstName" name="docFirstName" type="text" placeholder="e.g. John" required />
          </div>
          <div class="form-group">
            <label for="lastName">Last Name *</label>
            <input id="lastName" name="docLastName" type="text" placeholder="e.g. Doe" required />
          </div>
        </div>

        <div class="form-row">
          <div class="form-group">
            <label for="email">Email address *</label>
            <input id="email" name="docEmail" type="email" placeholder="doctor@cliniterra.com" required />
          </div>
          <div class="form-group">
            <label for="phone">Phone Number</label>
            <input id="phone" name="docPhone" type="tel" placeholder="+1 (555) 123-4567" />
          </div>
        </div>

        <div class="form-row">
          <div class="form-group">
            <label for="specialty">Specialty</label>
            <select id="specialty" name="docSpeciality">
              <option>Marine Medicine</option>
              <option>Cardiology</option>
              <option>Neurologist</option>
              <option>Gynologist</option>
              <option>Opthalmologist</option>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="form-group">
            <label for="dob">Date of Birth</label>
            <input id="dob" name="docDob" type="date" />
          </div>
          <div class="form-group radio-group">
            <label class="radio-option"><input type="radio" name="docGender" value="male"> Male</label>
            <label class="radio-option"><input type="radio" name="docGender" value="female"> Female</label>
            <label class="radio-option"><input type="radio" name="docGender" value="other"> Other</label>
          </div>
        </div>

        <div class="form-group">
          <label for="address">Address</label>
          <input id="address" name="docAddress" type="text" placeholder="e.g. 123 Street Name, City" />
        </div>

        <!-- Error message shown only above submit button -->
        <c:if test="${not empty error}">
          <div class="form-group error-message">
            ${error}
          </div>
        </c:if>

        <div class="form-actions">
          <button type="reset" class="btn-cancel">Discard</button>
          <button type="submit" class="btn-save">Add Doctor</button>
        </div>
      </form>

      <hr style="margin:2rem 0; border-color:#ccc;" />

      <!-- Remove Doctor -->
      <h2>Remove Doctor</h2>
      <form class="doctor-form" method="post" action="${pageContext.request.contextPath}/removeDoctor">
        <div class="form-row">
          <div class="form-group">
            <label for="removeDocId">Select Doctor</label>
            <select id="removeDocId" name="docId" required>
              <c:forEach var="d" items="${doctors}">
                <option value="${d.docId}">
                  ${d.docFirstName} ${d.docLastName}
                </option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="form-actions">
          <button type="submit" class="btn-save">Remove Doctor</button>
        </div>
      </form>

    </section>
  </main>
</body>
</html>
