package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.UserTrainingScheduleDAO;
import com.buba.gymApp.backend.model.treaningComponents.UserTrainingSchedule;
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

@Repository("postgresUserTrainingSchedule")
public class UserTrainingScheduleDataAccessService implements UserTrainingScheduleDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserTrainingScheduleDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert an userTrainingSchedule into database
     *
     * @param userId user id
     * @param trainingScheduleId training schedule id
     * @param startDate start date
     * @param endDate end date
     * @param comment a comment for the user
     * @return the userTrainingSchedule if it was been created successfully, false otherwise
     */
    @Override
    public UserTrainingSchedule insertUserTrainingSchedule(int userId, int trainingScheduleId, Date startDate, Date endDate, String comment){
        String sql = "INSERT INTO \"userTrainingSchedule\" (\"userId\", \"trainingScheduleId\", \"startDate\", \"endDate\", \"comment\") VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, trainingScheduleId);
                preparedStatement.setDate(3, new java.sql.Date(startDate.getTime()));
                preparedStatement.setDate(4, new java.sql.Date(endDate.getTime()));
                preparedStatement.setString(5, comment);

                return preparedStatement;
            }, keyHolder);
            return new UserTrainingSchedule(Objects.requireNonNull(keyHolder.getKey()).intValue(), startDate, endDate, trainingScheduleId, comment, userId);
        } catch (DataAccessException | NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select the UserTrainingSchedule by Id
     * @param id id
     * @return if exist the UserTrainingSchedule, otherwise null
     */
    @Override
    public UserTrainingSchedule selectUserTrainingScheduleById(int id){
        String sql = "SELECT * FROM \"userTrainingSchedule\" WHERE id = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), UserTrainingSchedule.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select all the UserTrainingSchedule of an user
     * @param userId user id
     * @return a list of all the userTrainingSchedule selected
     */
    @Override
    public List<UserTrainingSchedule> selectUserTrainingScheduleByUserId(int userId){
        String sql = "SELECT * FROM \"userTrainingSchedule\" WHERE \"userId\" = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, userId), UserTrainingSchedule.mapper());
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select all the UserTrainingSchedule of an user that are not expired yet
     * @param userId user id
     * @return a list of all the userTrainingSchedule selected
     */
    @Override
    public List<UserTrainingSchedule> selectNotExpiredUserTrainingScheduleByUserId(int userId){
        String sql = "SELECT * FROM \"userTrainingSchedule\" WHERE \"userId\" = ? AND \"endDate\" > now()";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, userId), UserTrainingSchedule.mapper());
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update the userTrainingSchedule by his id
     * @param userTrainingSchedule userTrainingSchedule
     * @return the userTrainingSchedule if it was been updated successfully, false otherwise
     */
    @Override
    public UserTrainingSchedule updateUserTrainingSchedule(UserTrainingSchedule userTrainingSchedule){
        String sql = "UPDATE \"userTrainingSchedule\" SET \"userId\" = ?, \"trainingScheduleId\" = ?, \"startDate\" = ?, \"endDate\" = ?, comment = ? WHERE id = ?";

        try {
            jdbcTemplate.update(sql, preparedStatement->{
                preparedStatement.setInt(1, userTrainingSchedule.getUserId());
                preparedStatement.setInt(2, userTrainingSchedule.getTrainingScheduleId());
                preparedStatement.setDate(3, new java.sql.Date(userTrainingSchedule.getStartDate().getTime()));
                preparedStatement.setDate(4, new java.sql.Date(userTrainingSchedule.getEndDate().getTime()));
                preparedStatement.setString(5, userTrainingSchedule.getComments());
            });
            return userTrainingSchedule;
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
