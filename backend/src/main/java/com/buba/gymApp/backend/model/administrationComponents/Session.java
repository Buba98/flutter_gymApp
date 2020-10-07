package com.buba.gymApp.backend.model.administrationComponents;

import java.util.Date;
import java.util.UUID;

public class Session {
    UUID id;
    int userId;
    Date dateExpiring;

    public Session(UUID id, int userId, Date dateExpiring) {
        this.id = id;
        this.userId = userId;
        this.dateExpiring = dateExpiring;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateExpiring() {
        return dateExpiring;
    }

    public void setDateExpiring(Date dateExpiring) {
        this.dateExpiring = dateExpiring;
    }
}
