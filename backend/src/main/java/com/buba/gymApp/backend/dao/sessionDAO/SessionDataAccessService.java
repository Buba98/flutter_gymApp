package com.buba.gymApp.backend.dao.sessionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SessionDataAccessService implements SessionDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SessionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID insertSession(int userId) {

        UUID id = null;

        while (id == null){
            id = UUID.randomUUID();
            if (selectSessionByUUID(id) != null)
                id = null;
        }

        String sql = "INSERT INTO session (uuid, userId, expiringDate) values (?, ?, ?)";

        jdbcTemplate.update(sql, new Object[]{id, userId, new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(31))});

        return id;
    }

    @Override
    public UUID selectSessionByUserId(int userId) {
        return null;
    }

    @Override
    public void deleteSessionByUserId(int userId) {

    }

    @Override
    public Integer selectSessionByUUID(UUID id) {
        return 0;
    }
}
