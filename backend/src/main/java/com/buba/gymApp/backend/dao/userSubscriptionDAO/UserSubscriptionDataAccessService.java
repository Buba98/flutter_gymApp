package com.buba.gymApp.backend.dao.userSubscriptionDAO;

import com.buba.gymApp.backend.dao.subscriptionDAO.SubscriptionDAO;
import com.buba.gymApp.backend.model.administrationComponents.UserSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
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
    private final SubscriptionDAO subscriptionDAO;

    @Autowired
    public UserSubscriptionDataAccessService(JdbcTemplate jdbcTemplate, @Qualifier("postgresSubscription") SubscriptionDAO subscriptionDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.subscriptionDAO = subscriptionDAO;
    }

    @Override
    public UserSubscription insertUserSubscription(UserSubscription userSubscription) {
        String sql = "INSERT INTO \"userSubscription\" (userid, subscriptionid, entrancedone, startdate, enddate) VALUES (?, ?, ?, ?, ?)";

        Object[] objects = new Object[]{userSubscription.getUserId(), userSubscription.getSubscriptionId(), userSubscription.getEntranceDone(), userSubscription.getStartDate(), userSubscription.getEndDate()};
        KeyHolder keyHolder = new GeneratedKeyHolder();

     try {
        jdbcTemplate.update(sql,objects, keyHolder);
    } catch (
    DataAccessException e) {
        e.printStackTrace();
        return null;
    }
        userSubscription.setId(keyHolder.getKey().intValue());
        return userSubscription;
    }

    @Override
    public boolean updateAll(UserSubscription userSubscription){
        String sql = "UPDATE \"userSubscription\" SET entrancedone = ?, startdate = ?, enddate = ? WHERE id = ?";

        Object[] objects = new Object[]{userSubscription.getEntranceDone(), userSubscription.getStartDate(), userSubscription.getEndDate(), userSubscription.getId()};

        jdbcTemplate.update(sql, objects);
        return true;
    }

    @Override
    public List<UserSubscription> getAllUserSubscriptionsByUserId(int userId){
        String sql = "SELECT * FROM \"userSubscription\" WHERE userid = ?";

        try {
        return jdbcTemplate.query(sql, new Object[]{userId}, (((resultSet, i) -> fromResultSetToUserSubscription(resultSet))));
    } catch (DataAccessException e) {
        e.printStackTrace();
        return null;
    }
    }

    @Override
    public List<Integer> getAllUserSubscriptionsIdsByUserId(int userId) {
        String sql = "SELECT id FROM \"userSubscription\" WHERE userid = ?";

        try {
            return jdbcTemplate.query(sql, new Object[]{userId}, (((resultSet, i) -> resultSet.getInt("id"))));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserSubscription> getAllNotExpiredUserSubscriptionsByUserId(int userId){
        String sql = "SELECT * FROM \"userSubscription\" WHERE userid = ? AND enddate > now()";
        try {
        List<UserSubscription> userSubscriptions = jdbcTemplate.query(sql, new Object[]{userId}, (((resultSet, i) -> fromResultSetToUserSubscription(resultSet))));
            userSubscriptions.removeIf(userSubscription -> subscriptionDAO.selectSubscriptionById(userSubscription.getSubscriptionId()).getMaxEntrances() > 0 && userSubscription.getEntranceDone() >= subscriptionDAO.selectSubscriptionById(userSubscription.getSubscriptionId()).getMaxEntrances());
            return userSubscriptions;
    } catch (DataAccessException e) {
        e.printStackTrace();
        return null;
    }
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
