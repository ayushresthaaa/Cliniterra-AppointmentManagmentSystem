package com.cliniterra.model;

public class BookScheduleModel {
    private int schedule_id;
    private String schedule_day;
    private String start_time;
    private String end_time;

    public int getSchedule_id() { return schedule_id; }
    public void setSchedule_id(int schedule_id) { this.schedule_id = schedule_id; }
    public String getSchedule_day() { return schedule_day; }
    public void setSchedule_day(String schedule_day) { this.schedule_day = schedule_day; }
    public String getStart_time() { return start_time; }
    public void setStart_time(String start_time) { this.start_time = start_time; }
    public String getEnd_time() { return end_time; }
    public void setEnd_time(String end_time) { this.end_time = end_time; }
}
