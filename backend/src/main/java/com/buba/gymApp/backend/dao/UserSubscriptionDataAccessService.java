package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.UserSubscriptionDAO;
import com.buba.gymApp.backend.model.administrationComponents.UserSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository("postgresUserSubscription")
public class UserSubscriptionDataAccessService implements UserSubscriptionDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserSubscriptionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert user subscription into DB
     * @param id id
     * @param subscriptionId subscription id
     * @param entranceDone entrance done
     * @param userId user id
     * @param endDate end date
     * @return the userSubscription if it is inserted into DB, null otherwise
     */
    @Override
    public UserSubscription insertUserSubscription(int id, int subscriptionId, int entranceDone, int userId, Date endDate) {
        String sql = "INSERT INTO \"userSubscription\" (id, \"userId\", \"subscriptionId\", \"entranceDone\", \"endDate\") VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, userId);
                preparedStatement.setInt(3, subscriptionId);
                preparedStatement.setInt(4, entranceDone);
                preparedStatement.setDate(5, new java.sql.Date(endDate.getTime()));

                return preparedStatement;
            }, keyHolder);
            return new UserSubscription(Objects.requireNonNull(keyHolder.getKey()).intValue(), subscriptionId, entranceDone, userId, endDate);
        } catch (DataAccessException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * update an user subscription
     * @param userSubscription user subscription updated
     * @return the user subscription updated if it was been updated, null otherwise
     */
    @Override
    public UserSubscription updateUserSubscription(UserSubscription userSubscription){
        String sql = "UPDATE \"userSubscription\" SET \"userId\" = ?, \"subscriptionId\" = ?, \"entranceDone\" = ?, \"endDate\" = ? WHERE id = ?";

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, userSubscription.getUserId());
                preparedStatement.setInt(2, userSubscription.getSubscriptionId());
                preparedStatement.setInt(3, userSubscription.getEntranceDone());
                preparedStatement.setDate(4, new java.sql.Date(userSubscription.getEndDate().getTime()));
                preparedStatement.setInt(5, userSubscription.getId());

                return preparedStatement;
            });
            return userSubscription;
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select all the user subscription by user id
     * @param userId user id
     * @return a list of all user subscriptions if at least one has been found, otherwise null
     */
    @Override
    public List<UserSubscription> selectAllUserSubscriptionsByUserId(int userId) {
        String sql = "SELECT * FROM \"userSubscription\" WHERE \"userId\" = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, userId), UserSubscription.mapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * select the not expired user subscription
     * @param userId user id
     * @return the not expired user subscription, null if there is none
     */
    @Override
    public UserSubscription selectNotExpiredUserSubscriptionsByUserId(int userId) {
        String sql = "SELECT * FROM \"userSubscription\" WHERE \"userId\" = ? AND \"endDate\" > now()";
        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, userId), UserSubscription.mapper()).get(0);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}