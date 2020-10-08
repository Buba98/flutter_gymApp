package com.buba.gymApp.backend.dao.subscriptionDAO;

import com.buba.gymApp.backend.model.administrationComponents.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("postgresSubscription")
public class SubscriptionDataAccessService implements  SubscriptionDAO{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubscriptionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Subscription insertSubscription(Subscription subscription) {

        if (selectSubscriptionByEverything(subscription) != null)
            return subscription;

        String sql = "INSERT INTO subscription (mouthduration, cost, maxentrance) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, subscription.getMouthDuration(), subscription.getCost(), subscription.getMaxEntrances(), keyHolder);

        subscription.setId(keyHolder.getKey().intValue());

        return subscription;
    }

    @Override
    public Subscription selectSubscriptionByEverything(Subscription subscription) {
        String sql = "SELECT * FROM subscription WHERE mouthduration = ? AND cost = ? AND maxentrance = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{subscription.getMouthDuration(), subscription.getCost(), subscription.getMaxEntrances()}, ((resultSet, i) -> fromResultSetToSubscription(resultSet)));
    }

    @Override
    public boolean deleteSubscriptionById(int id) {
        String sql = "DELETE FROM subscription WHERE id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }

    @Override
    public Subscription selectSubscriptionById(int id) {
        String sql = "SELECT * FROM subscription WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> fromResultSetToSubscription(resultSet)));
    }

    private Subscription fromResultSetToSubscription(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int mouthDuration = resultSet.getInt("monthduration");
        float cost = resultSet.getFloat("cost");
        int maxEntrances = resultSet.getInt("maxentrance");

        return new Subscription(id, mouthDuration, cost, maxEntrances);
    }
}
