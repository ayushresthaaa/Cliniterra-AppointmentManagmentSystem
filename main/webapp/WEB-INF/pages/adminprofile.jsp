<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Admin Profile</title>
  <!-- reuse the same CSS your editSchedule.jsp uses -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editSchedule.css">
</head>
<body>

  <!-- Sidebar (copied from editSchedule.jsp) -->
  <div class="sidebar">
    <a href="${pageContext.request.contextPath}/admindashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/editschedule">Schedules</a>
    <a href="${pageContext.request.contextPath}/finddoctor">Doctors</a>
    <a href="${pageContext.request.contextPath}/viewpatients">Patients</a>
    <a href="${pageContext.request.contextPath}/adddoctor">Add Doctors</a>
    <a href="${pageContext.request.contextPath}/settings">Settings</a>
  </div>

  <!-- Main Content -->
  <div class="container">
    <c:if test="${updateSuccess}">
      <div class="success-msg">Profile updated successfully!</div>
    </c:if>
    <c:if test="${updateError != null}">
      <div class="error-msg">${updateError}</div>
    </c:if>

    <form class="profile-card" method="post" action="${pageContext.request.contextPath}/viewadminprofile">
      <h2>Admin Profile</h2>

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
            <input type="text" name="firstName" value="${admin.firstName}" />
          </div>
          <div>
            <label><strong>Last Name:</strong></label>
            <input type="text" name="lastName" value="${admin.lastName}" />
          </div>
          <div>
            <label><strong>Username:</strong></label>
            <input type="text" name="username" value="${admin.userName}" readonly />
          </div>
          <div>
            <label><strong>Email:</strong></label>
            <input type="email" name="email" value="${admin.email}" />
          </div>
          <div>
            <label><strong>Phone:</strong></label>
            <input type="text" name="phone" value="${admin.number}" />
          </div>
          <div>
            <label><strong>Date of Birth:</strong></label>
            <input type="date" name="dob" value="${admin.dob}" />
          </div>
        </div>
      </div>

      <div class="action-buttons">
        <button type="submit">Save</button>
      </div>
    </form>
  </div>

</body>
</html>
