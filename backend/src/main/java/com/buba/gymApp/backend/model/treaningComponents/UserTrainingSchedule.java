package com.buba.gymApp.backend.model.treaningComponents;

import com.buba.gymApp.backend.utils.Converters;
import com.google.gson.JsonObject;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

public class UserTrainingSchedule {
    private int id;
    private int userId;
    private int trainingScheduleId;
    private Date startDate;
    private Date endDate;
    private String comments;

    public UserTrainingSchedule(int id, Date startDate, Date endDate, int trainingScheduleId, String comments, int userId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainingScheduleId = trainingScheduleId;
        this.comments = comments;
        this.userId = userId;
    }

    private UserTrainingSchedule(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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


    public static RowMapper<UserTrainingSchedule> mapper(){
        return (resultSet, i) -> {
            UserTrainingSchedule userTrainingSchedule = new UserTrainingSchedule();
            userTrainingSchedule.id = resultSet.getInt("id");
            userTrainingSchedule.startDate = resultSet.getTimestamp("startDate");
            userTrainingSchedule.endDate = resultSet.getTimestamp("endDate");
            userTrainingSchedule.comments = resultSet.getString("comment");
            userTrainingSchedule.trainingScheduleId = resultSet.getInt("trainingScheduleId");
            userTrainingSchedule.userId = resultSet.getInt("userId");

            return  userTrainingSchedule;
        };
    }

    public JsonObject json(){
        JsonObject toReturn = new JsonObject();

        toReturn.addProperty("startDate", startDate.toString());
        toReturn.addProperty("endDate", endDate.toString());
        toReturn.addProperty("comment", comments);
        toReturn.addProperty("trainingScheduleId", trainingScheduleId);

        return toReturn;
    }
}
