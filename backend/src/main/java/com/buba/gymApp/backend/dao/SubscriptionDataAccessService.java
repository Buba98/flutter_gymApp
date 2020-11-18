package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.SubscriptionDAO;
import com.buba.gymApp.backend.model.administrationComponents.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

@Repository("postgresSubscription")
public class SubscriptionDataAccessService implements SubscriptionDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubscriptionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert a new subscription into DB
     * @param mouthDuration duration of the subscription
     * @param cost cost of subscription
     * @param maxEntrances max entrances that can be done
     * @param name name of the subscription
     * @return the new Subscription that has been created, null otherwise
     */
    @Override
    public Subscription insertSubscription(int mouthDuration, float cost, int maxEntrances, String name) {

        String sql = "INSERT INTO subscription (\"mouthDuration\", cost, \"maxEntrances\", name) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setInt(1, mouthDuration);
                preparedStatement.setFloat(2, cost);
                preparedStatement.setInt(3, maxEntrances);
                preparedStatement.setString(4, name);
                return preparedStatement;
            }, keyHolder);
            return new Subscription(Objects.requireNonNull(keyHolder.getKey()).intValue(), name, mouthDuration, cost, maxEntrances);
        } catch (DataAccessException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Delete subscription from Db
     * @param id id
     * @return true if it has been deleted, false otherwise
     */
    @Override
    public boolean deleteSubscriptionById(int id) {
        String sql = "DELETE FROM subscription WHERE id = ?";
        try {
            return jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                return preparedStatement;
            }) == 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Select subscription from database by id
     * @param id id
     * @return the subscription if it has been found, null otherwise
     */
    @Override
    public Subscription selectSubscriptionById(int id) {
        String sql = "SELECT * FROM subscription WHERE id = ?";
        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), Subscription.mapper()).get(0);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select subscription from database by name
     * @param name name
     * @return the subscription if it has been found, null otherwise
     */
    @Override
    public Subscription selectSubscriptionByName(String name){
        String sql = "SELECT * FROM subscription WHERE name = ?";
        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, name), Subscription.mapper()).get(0);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
