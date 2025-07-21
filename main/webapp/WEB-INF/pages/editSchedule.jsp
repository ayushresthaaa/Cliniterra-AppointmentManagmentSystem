<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Edit Schedule</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editSchedule.css">
</head>
<body>

  <!-- Sidebar -->
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
    <h2>Edit Doctor Schedule</h2>

    <!-- 1. Doctor Selector (GET request on change) -->
    <form action="${pageContext.request.contextPath}/editSchedule" method="get">
      <label for="docId">Doctor Full Name</label>
      <select id="docId" name="docId" onchange="this.form.submit()">
        <c:forEach var="d" items="${doctors}">
          <option value="${d.docId}" <c:if test="${d.docId == selectedDocId}">selected</c:if>>
            ${d.docFirstName} ${d.docLastName}
          </option>
        </c:forEach>
      </select>
    </form>

    <!-- 2. Schedule Update Form (POST) -->
    <form action="${pageContext.request.contextPath}/editSchedule" method="post">
      <input type="hidden" name="docId" value="${selectedDocId}" />

      <!-- Email (read-only) -->
      <label for="email">Email</label>
      <input type="email" id="email" name="email" readonly
             value="<c:forEach var='d' items='${doctors}'>
                      <c:if test='${d.docId == selectedDocId}'>
                        ${d.docEmail}
                      </c:if>
                    </c:forEach>" />

      <h3>Assign One Day (9:00 AM – 4:00 PM)</h3>

      <div class="schedule-row">
        <label for="scheduleId">Day</label>
        <select id="scheduleId" name="scheduleId">
          <c:forEach var="s" items="${schedules}">
            <option value="${s.scheduleId}" <c:if test="${s.scheduleId == currentScheduleId}">selected</c:if>>
              ${s.scheduleDay}
            </option>
          </c:forEach>
        </select>
      </div>

      <button type="submit" class="save-btn">Save Schedule</button>
    </form>
  </div>

</body>
</html>
