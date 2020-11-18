package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.SubscriptionDAO;
import com.buba.gymApp.backend.model.administrationComponents.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("postgresSubscription")
public class SubscriptionDataAccessService implements SubscriptionDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubscriptionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Subscription insertSubscription(Subscription subscription) {

        String sql = "INSERT INTO subscription (\"mouthDuration\", cost, \"maxEntrances\") values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(sql, subscription.getMouthDuration(), subscription.getCost(), subscription.getMaxEntrances(), keyHolder);
            subscription.setId(keyHolder.getKey().intValue());
            return subscription;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Subscription selectSubscriptionByEverything(Subscription subscription) {
        String sql = "SELECT * FROM subscription WHERE \"mouthDuration\" = ? AND cost = ? AND \"maxEntrances\" = ?";
        try {
        return jdbcTemplate.queryForObject(sql, new Object[]{subscription.getMouthDuration(), subscription.getCost(), subscription.getMaxEntrances()}, ((resultSet, i) -> fromResultSetToSubscription(resultSet)));
    }
        catch (DataAccessException e){
        e.printStackTrace();
        return null;
    }
    }

    @Override
    public boolean deleteSubscriptionById(int id) {
        String sql = "DELETE FROM subscription WHERE id = ?";
        try {
        return jdbcTemplate.update(sql, id) == 1;
    }
        catch (DataAccessException e){
        e.printStackTrace();
        return false;
    }
    }

    @Override
    public Subscription selectSubscriptionById(int id) {
        String sql = "SELECT * FROM subscription WHERE id = ?";
        try {
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> fromResultSetToSubscription(resultSet)));
    }
        catch (DataAccessException e){
        e.printStackTrace();
        return null;
    }
    }

    private Subscription fromResultSetToSubscription(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int mouthDuration = resultSet.getInt("monthDuration");
        float cost = resultSet.getFloat("cost");
        int maxEntrances = resultSet.getInt("maxEntrance");

        return new Subscription(id, mouthDuration, cost, maxEntrances);
    }
}
