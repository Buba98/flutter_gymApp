package com.buba.gymApp.backend.model.administrationComponents;


import org.springframework.jdbc.core.RowMapper;

import java.util.UUID;

public class Session {
    UUID uuid;
    int userId;

    public Session(UUID uuid, int userId) {
        this.uuid = uuid;
        this.userId = userId;
    }

    private Session(){}

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static RowMapper<Session> mapper(){
        return (resultSet, i) -> {
            Session session = new Session();
            session.uuid = resultSet.getObject("uuid", UUID.class);
            session.userId = resultSet.getInt("userId");

            return session;
        };
    }
}
