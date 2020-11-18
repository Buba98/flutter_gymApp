package com.buba.gymApp.backend.model.administrationComponents;

import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

public class UserSubscription {
    private int id;
    private int subscriptionId;
    private int entranceDone;
    private int userId;
    private Date endDate;

    public UserSubscription(int id, int subscriptionId, int entranceDone, int userId, Date endDate) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.entranceDone = entranceDone;
        this.userId = userId;
        this.endDate = endDate;
    }

    private UserSubscription(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getEntranceDone() {
        return entranceDone;
    }

    public void setEntranceDone(int entranceDone) {
        this.entranceDone = entranceDone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static RowMapper<UserSubscription> mapper(){
        return (resultSet, i) -> {
            UserSubscription userSubscription = new UserSubscription();
            userSubscription.endDate = new Date(resultSet.getDate("endDate").getTime());
            userSubscription.entranceDone = resultSet.getInt("entranceDone");
            userSubscription.id = resultSet.getInt("id");
            userSubscription.subscriptionId = resultSet.getInt("subscriptionId");
            userSubscription.userId = resultSet.getInt("userId");

            return userSubscription;
        };
    }
}
