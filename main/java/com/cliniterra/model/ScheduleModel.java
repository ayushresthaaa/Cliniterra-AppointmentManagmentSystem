
package com.cliniterra.model;

import java.time.LocalTime;

public class ScheduleModel {
    private int scheduleId;
    private String scheduleDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private int slotNum;

    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }

    public String getScheduleDay() { return scheduleDay; }
    public void setScheduleDay(String scheduleDay) { this.scheduleDay = scheduleDay; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public int getSlotNum() { return slotNum; }
    public void setSlotNum(int slotNum) { this.slotNum = slotNum; }
}
