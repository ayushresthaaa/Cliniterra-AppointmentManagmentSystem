<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>View Doctors (Admin)</title>
  <!-- keeps your existing sidebar/table CSS intact -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editSchedule.css" />
</head>
<body>

  <!-- Sidebar -->
  <div class="sidebar">
    <a href="${pageContext.request.contextPath}/admindashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/editSchedule">Schedules</a>
    <a href="${pageContext.request.contextPath}/viewdoctorsadmin" class="active">Doctors</a>
    <a href="${pageContext.request.contextPath}/viewpatients">Patients</a>
    <a href="${pageContext.request.contextPath}/adddoctor">Add Doctors</a>
    <a href="${pageContext.request.contextPath}/settings">Settings</a>
  </div>

  <!-- Main Content -->
  <div class="container">
    <h2>All Doctors</h2>

    <div class="table-wrapper">
      <table style="
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 10px;
            margin-top: 20px;
            ">
        <thead>
          <tr>
            <th style="
                  padding: 12px 15px;
                  text-align: left;
                  background-color: #f2f2f2;
                  border-bottom: 2px solid #ccc;
                  ">Name</th>
            <th style="
                  padding: 12px 15px;
                  text-align: left;
                  background-color: #f2f2f2;
                  border-bottom: 2px solid #ccc;
                  ">Speciality</th>
            <th style="
                  padding: 12px 15px;
                  text-align: left;
                  background-color: #f2f2f2;
                  border-bottom: 2px solid #ccc;
                  ">Phone</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="d" items="${doctors}">
            <tr style="background-color: #fff; border: 1px solid #eee;">
              <td style="padding: 12px 15px;">${d.docFirstName} ${d.docLastName}</td>
              <td style="padding: 12px 15px;">${d.docSpeciality}</td>
              <td style="padding: 12px 15px;">${d.docPhone}</td>
            </tr>
          </c:forEach>
          <c:if test="${empty doctors}">
            <tr>
              <td colspan="3" style="padding: 12px 15px; text-align: center;">
                No doctors found.
              </td>
            </tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>

</body>
</html>
