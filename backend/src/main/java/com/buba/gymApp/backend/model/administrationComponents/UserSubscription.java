package com.buba.gymApp.backend.model.administrationComponents;

import java.util.Date;

public class UserSubscription {
    private int id;
    private int subscriptionId;
    private int entranceDone;
    private int userId;
    private Date startDate;
    private Date endDate;

    public UserSubscription(int id, int subscriptionId, int entranceDone, int userId, Date startDate, Date endDate) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.entranceDone = entranceDone;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

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
}
