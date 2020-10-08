package com.buba.gymApp.backend.dao.userSubscriptionDAO;

import com.buba.gymApp.backend.model.administrationComponents.UserSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository("postgresUserSubscription")
public class UserSubscriptionDataAccessService implements UserSubscriptionDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserSubscriptionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserSubscription insertUserSubscription(UserSubscription userSubscription) {
        String sql = "INSERT INTO \"userSubscription\" (userid, subscriptionid, entrancedone, startdate, enddate) VALUES (?, ?, ?, ?, ?)";

        Object[] objects = new Object[]{userSubscription.getUserId(), userSubscription.getSubscriptionId(), userSubscription.getEntranceDone(), userSubscription.getStartDate(), userSubscription.getEndDate()};
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql,objects, keyHolder);

        userSubscription.setId(keyHolder.getKey().intValue());
        return userSubscription;
    }

    @Override
    public List<UserSubscription> getAllUserSubscriptionsByUserId(int userId){
        String sql = "SELECT * FROM \"userSubscription\" WHERE userid = ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, (((resultSet, i) -> fromResultSetToUserSubscription(resultSet))));
    }

    @Override
    public List<Integer> getAllUserSubscriptionsIdsByUserId(int userId) {
        String sql = "SELECT id FROM \"userSubscription\" WHERE userid = ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, (((resultSet, i) -> resultSet.getInt("id"))));
    }

    @Override
    public List<UserSubscription> getAllNotExpiredUserSubscriptionsByUserId(int userId){
        String sql = "SELECT * FROM \"userSubscription\" WHERE userid = ? AND enddate > now()";

        return jdbcTemplate.query(sql, new Object[]{userId}, (((resultSet, i) -> fromResultSetToUserSubscription(resultSet))));
    }

    private UserSubscription fromResultSetToUserSubscription(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("userid");
        int subscriptionId = resultSet.getInt("subscriptionid");
        int entranceDone = resultSet.getInt("entrancedone");
        Date dateStart = resultSet.getDate("startdate");
        Date dateEnd = resultSet.getDate("enddate");

        return new UserSubscription(id, subscriptionId, entranceDone, userId, dateStart, dateEnd);
    }
}
