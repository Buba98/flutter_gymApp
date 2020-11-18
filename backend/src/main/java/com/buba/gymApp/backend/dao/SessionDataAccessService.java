package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.SessionDAO;
import com.buba.gymApp.backend.model.administrationComponents.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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

    /**
     * Create a new session for an user
     * @param userId user id
     * @return the session that has been created, null false otherwise
     */
    @Override
    public Session insertSession(int userId) {

        deleteSessionByUserId(userId);

        UUID uuid = createValidUUID();

        String sql = "INSERT INTO session (uuid, \"userId\") values (?, ?)";

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, uuid);
                preparedStatement.setInt(2, userId);
                return preparedStatement;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

        return new Session(uuid, userId);
    }

    /**
     * Support method to create a new uuid
     * @return new uuid not present in the database
     */
    private UUID createValidUUID(){
        UUID uuid = null;

        while (uuid == null) {
            uuid = UUID.randomUUID();
            if (selectSessionByUUID(uuid) != null)
                uuid = null;
        }

        return uuid;
    }

    /**
     * Select the session of the user
     * @param userId user id
     * @return the Session if it exist, otherwise null
     */
    @Override
    public Session selectSessionByUserId(int userId) {
        String sql = "SELECT * FROM session WHERE \"userId\" = ?";
        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, userId), Session.mapper()).get(0);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Remove the session from DB
     * @param userId user id
     * @return true if has been removed, false otherwise
     */
    @Override
    public boolean deleteSessionByUserId(int userId) {
        String sql = "DELETE FROM session WHERE \"userId\" = ?";
        try {
            return jdbcTemplate.update(sql, preparedStatement -> {
                preparedStatement.setInt(1, userId);
            }) == 1;
        }
        catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Remove the session from DB
     * @param uuid uuid
     * @return true if has been removed, false otherwise
     */
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

    /**
     * Select the session by his uuid
     * @param uuid uuid
     * @return the session it has been found, null otherwise
     */
    @Override
    public Session selectSessionByUUID(UUID uuid) {
        String sql = "SELECT * FROM session WHERE uuid = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setObject(1, uuid), Session.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
