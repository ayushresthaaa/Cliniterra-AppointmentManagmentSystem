<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dr. ${doctor.docFirstName} ${doctor.docLastName} - Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/viewdoctor.css">
</head>
<body>
    <!--Navbar-->
    <div class="navbar"> 
        <img class="logo" src="${pageContext.request.contextPath}/resources/images/clinilogobgrem.png" alt="Logo">
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/home">Home</a>
            <a href="${pageContext.request.contextPath}/finddoctor">Find doctor</a>
            <a href="${pageContext.request.contextPath}/view">View appointment</a>
            <a href="${pageContext.request.contextPath}/aboutus">About us</a>
            <a href="${pageContext.request.contextPath}/contact">Contact</a>
        </div>
        <a href="${pageContext.request.contextPath}/book?doc_id=${doctor.docId}" class="button">Book now</a>
    </div>

    <!--Doctor Profile Info-->
    <div class="profile-container" style="margin-top: 80px;">
        <div class="profile-header">
            <div class="doctor-info">
                <h1>DR. ${doctor.docFirstName} ${doctor.docLastName}</h1>
                <h2>${doctor.docSpeciality}</h2>
                <p><b>Email:</b> ${doctor.docEmail}</p>
                <p><b>Phone:</b> ${doctor.docPhone}</p>
                <p><b>Gender:</b> ${doctor.docGender}</p>
                <p><b>DOB:</b> ${doctor.docDob}</p>
                <p><b>Address:</b> ${doctor.docAddress}</p>
            </div>
        </div>

        <div class="profile-content">
            <div class="sidebar">
                <h3>OPD SCHEDULE</h3>
                <div class="opd-schedule">
                    <c:choose>
                        <c:when test="${not empty scheduleList}">
                            <c:forEach var="sch" items="${scheduleList}">
                                <p>
                                    <b>${sch.schedule_day}</b>: 
                                    ${sch.start_time} - ${sch.end_time}
                                </p>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>Schedule not available</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="main-content"></div>
        </div>
    </div>
</body>
</html>
