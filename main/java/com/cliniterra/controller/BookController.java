package com.cliniterra.controller;

import com.cliniterra.model.DoctorModel;
import com.cliniterra.model.BookScheduleModel;
import com.cliniterra.service.BookService;
import com.cliniterra.service.UserService;
import com.cliniterra.service.ViewDoctorService;
import com.cliniterra.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/book" })
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ViewDoctorService viewDoctorService;
    private BookService bookService;
    private UserService userService;

    public BookController() {
        super();
        viewDoctorService = new ViewDoctorService();
        bookService = new BookService();
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String docIdParam = request.getParameter("doc_id");
        DoctorModel doctor = null;
        List<BookScheduleModel> schedules = null;

        if (docIdParam != null) {
            try {
                int docId = Integer.parseInt(docIdParam);
                doctor = viewDoctorService.getDoctorById(docId);
                schedules = bookService.getSchedulesForDoctor(docId);
            } catch (NumberFormatException e) {
                request.setAttribute("bookingError", "Invalid doctor ID.");
            }
        }
        request.setAttribute("doctor", doctor);
        request.setAttribute("schedules", schedules);
        request.getRequestDispatcher("WEB-INF/pages/book.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String docIdParam = request.getParameter("doc_id");
        String scheduleIdParam = request.getParameter("schedule_id");
        String appointmentDate = request.getParameter("appointment_date");

        boolean success = false;
        String bookingError = null;

        String username = SessionUtil.getUsername(request);

        if (username != null && docIdParam != null && scheduleIdParam != null && appointmentDate != null) {
            try {
                int docId = Integer.parseInt(docIdParam);
                int scheduleId = Integer.parseInt(scheduleIdParam);

                int userId = userService.getUserIdByUsername(username);
                if (userId == -1) {
                    bookingError = "User not found in system.";
                } else {
                    bookingError = bookService.bookAppointmentWithError(userId, docId, scheduleId, appointmentDate);
                    if (bookingError == null) {
                        success = true;
                    }
                }
            } catch (NumberFormatException e) {
                bookingError = "Invalid input data.";
            }
        } else {
            bookingError = "Please fill all required fields.";
        }

        if (success) {
            request.setAttribute("bookingSuccess", true);
        } else {
            request.setAttribute("bookingError", bookingError != null ? bookingError : "Booking failed. Please try again.");
        }

        doGet(request, response);
    }
}
