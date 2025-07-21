<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--@author Aayush Shrestha-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet' />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/admindashboard.css" />
    <title>Admin Dashboard</title>
</head>
<body>

<div class="container">
    <div class="leftsidebar">
        <h1>Admin <br> Dashboard</h1>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/viewadminprofile" class="profile">
                    <i class='bx bxs-user-account'></i>
                    <span class="text">Profile</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/logout" class="profile">
                    <i class='bx bxs-user-account'></i>
                    <span class="text">Log Out</span>
                </a>
            </li>
        </ul>
    </div>

    <div class="main">
        <div class="toppart">
            <div class="card">
                <div class="first" onclick="location.href='${pageContext.request.contextPath}/addDoc'">
                    <h3><i class='bx bxs-user-account'></i>Add Doctors</h3>
                </div>
            </div>
            <div class="card">
                <div class="first" onclick="location.href='${pageContext.request.contextPath}/viewdoctorsadmin'">
                    <h3><i class='bx bxs-user-account'></i>View Doctors</h3>
                </div>
            </div>
            <div class="card" onclick="location.href='${pageContext.request.contextPath}/editSchedule'">
                <div class="first">
                    <h3><i class='bx bxs-user-account'></i>Edit Schedule</h3>
                </div>
            </div>
        </div>

        <div class="bottompart">
            <h2>Change The Status of Patients</h2>
            <div class="controls">
                <select class="select">
                    <option>All</option>
                    <option>Not Completed</option>
                    <option>Completed</option>
                </select>
            </div>

            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Username</th>
                            <th>Appointment Date</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ua" items="${appointments}">
                            <tr>
                                <td>${ua.firstName} ${ua.lastName}</td>
                                <td>${ua.username}</td>
                                <td>
                                    <fmt:formatDate value="${ua.appointmentDate}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td><span class="status">${ua.appointmentStatus}</span></td>
                                <td>
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/admindashboard"
                                          onsubmit="return confirm('Delete appointment for ${ua.username}?');">
                                        <input type="hidden" name="username" value="${ua.username}" />
                                        <button type="submit" class="btn">Change</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty appointments}">
                            <tr>
                                <td colspan="5" style="text-align:center;">No appointments found.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
