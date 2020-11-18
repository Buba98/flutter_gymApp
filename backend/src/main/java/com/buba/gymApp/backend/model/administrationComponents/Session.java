package com.buba.gymApp.backend.model.administrationComponents;

import java.util.UUID;

public class Session {
    UUID id;
    int userId;

    public Session(UUID id, int userId) {
        this.id = id;
        this.userId = userId;
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
}
