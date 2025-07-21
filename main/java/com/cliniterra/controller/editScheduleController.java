package com.cliniterra.controller;

import com.cliniterra.model.DoctorModel;
import com.cliniterra.model.ScheduleModel;
import com.cliniterra.service.FindDocService;
import com.cliniterra.service.ScheduleService;
import com.cliniterra.service.DoctorScheduleService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(asyncSupported = true, urlPatterns = { "/editSchedule" })
public class EditScheduleController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final FindDocService        docService    = new FindDocService();
    private final ScheduleService       schedService  = new ScheduleService();
    private final DoctorScheduleService dsService     = new DoctorScheduleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // 1) load all doctors & all schedule slots
            List<DoctorModel> doctors     = docService.getAllDoctors();
            List<ScheduleModel> schedules = schedService.getAllSchedules();

            // 2) pick which doctor is “selected”
            String docIdParam = req.getParameter("docId");
            int selectedDocId = (docIdParam != null)
                ? Integer.parseInt(docIdParam)
                : (doctors.isEmpty() ? -1 : doctors.get(0).getDocId());

            // 3) find that doctor’s current schedule
            int currentSchedId = dsService.getScheduleIdByDoctor(selectedDocId);

            // 4) find all schedule IDs already taken
            Set<Integer> taken = dsService.getAllAssignedScheduleIds();

            // 5) build a list of only those schedules the user may choose:
            //    – the doctor’s own current day (so they can re-select it)
            //    – any day not yet taken by someone else
            List<ScheduleModel> available = new ArrayList<>();
            for (ScheduleModel s : schedules) {
                if (s.getScheduleId() == currentSchedId
                 || !taken.contains(s.getScheduleId())) {
                    available.add(s);
                }
            }

            // 6) expose to JSP
            req.setAttribute("doctors", doctors);
            req.setAttribute("schedules", available);
            req.setAttribute("selectedDocId", selectedDocId);
            req.setAttribute("currentScheduleId", currentSchedId);

            req.getRequestDispatcher("/WEB-INF/pages/editSchedule.jsp")
               .forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Unable to load schedule data", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int docId   = Integer.parseInt(req.getParameter("docId"));
            int schedId = Integer.parseInt(req.getParameter("scheduleId"));

            dsService.updateDoctorSchedule(docId, schedId);

            // redirect back so the user sees the updated assignment
            resp.sendRedirect(req.getContextPath()
                + "/editschedule?docId=" + docId);
        } catch (SQLException e) {
            throw new ServletException("Unable to save schedule", e);
        }
    }
}
