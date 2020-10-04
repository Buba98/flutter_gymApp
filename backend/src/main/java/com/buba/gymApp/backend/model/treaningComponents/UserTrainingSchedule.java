package com.buba.gymApp.backend.model.treaningComponents;

import java.util.Date;

public class UserTrainingSchedule {
    private int id;
    private Date startDate;
    private Date endDate;
    private int trainingScheduleId;
    private String comments;

    public UserTrainingSchedule(int id, Date startDate, Date endDate, int trainingScheduleId, String comments) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainingScheduleId = trainingScheduleId;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTrainingScheduleId() {
        return trainingScheduleId;
    }

    public void setTrainingScheduleId(int trainingScheduleId) {
        this.trainingScheduleId = trainingScheduleId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
