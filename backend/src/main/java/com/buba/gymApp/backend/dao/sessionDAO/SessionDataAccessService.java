package com.buba.gymApp.backend.dao.sessionDAO;

import com.buba.gymApp.backend.model.administrationComponents.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository("postgresSession")
public class SessionDataAccessService implements SessionDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SessionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session insertSession(int userId) {

        deleteSessionByUserId(userId);

        UUID id = null;

        while (id == null) {
            id = UUID.randomUUID();
            if (selectSessionByUUID(id) != null)
                id = null;
        }

        String sql = "INSERT INTO session (uuid, userid, dateexpiring) values (?, ?, ?)";

        Session session = new Session(id, userId, new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(31)));

        try {
            jdbcTemplate.update(sql, session.getId(), session.getUserId(), session.getDateExpiring());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

        return session;
    }

    @Override
    public Session selectSessionByUserId(int userId) {
        String sql = "SELECT * FROM session WHERE userid = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, ((resultSet, i) -> fromResultSetToSession(resultSet)));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteSessionByUserId(int userId) {
        String sql = "DELETE FROM session WHERE userid = ?";
        try {
            return jdbcTemplate.update(sql, userId) == 1;
        }
        catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSessionByUUID(UUID uuid) {
        String sql = "DELETE FROM session WHERE uuid = ?";
        try {
            return jdbcTemplate.update(sql, uuid) == 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Session selectSessionByUUID(UUID id) {
        String sql = "SELECT * FROM session WHERE uuid = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> fromResultSetToSession(resultSet)));
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    private Session fromResultSetToSession(ResultSet resultSet) throws SQLException {
        UUID id = resultSet.getObject("uuid", java.util.UUID.class);
        int userId = resultSet.getInt("userid");
        Date dateExpiring = resultSet.getTimestamp("dateexpiring");

        return new Session(id, userId, dateExpiring);
    }
}
