package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.TrainingScheduleDAO;
import com.buba.gymApp.backend.model.treaningComponents.TrainingSchedule;
import com.buba.gymApp.backend.utils.PostgreSQLInt4Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

@Repository("postgresTrainingSchedule")
public class TrainingScheduleDataAccessService implements TrainingScheduleDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public TrainingScheduleDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert a training schedule into DB
     * @param name name
     * @param description description
     * @param trainingsIds ids of the trainings of the training schedule
     * @return the new trainingSchedule if it has been created, false otherwise
     */
    @Override
    public TrainingSchedule insertTrainingSchedule(String name, String description, int[] trainingsIds){
        String sql = "INSERT INTO \"trainingSchedule\" (name, description, \"trainingsIds\") VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();


        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, description);
                preparedStatement.setArray(3, new PostgreSQLInt4Array(trainingsIds));
                return preparedStatement;
            }, keyHolder);
            return new TrainingSchedule(Objects.requireNonNull(keyHolder.getKey()).intValue(), name, trainingsIds, description);
        } catch (DataAccessException | NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select a trainingSchedule by id
     * @param id id
     * @return the training schedule if it has been found, null otherwise
     */
    @Override
    public TrainingSchedule selectTrainingScheduleById(int id){
        String sql = "SELECT * FROM \"trainingSchedule\" WHERE id = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), TrainingSchedule.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select training schedule by name
     * @param name name
     * @return the training schedule if it has been found, null otherwise
     */
    @Override
    public TrainingSchedule selectTrainingScheduleByName(String name){
        String sql = "SELECT * FROM \"trainingSchedule\" WHERE name = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, name), TrainingSchedule.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update a training schedule
     * @param trainingSchedule training schedule updated
     * @return the training schedule updated if it has been updated, null otherwise
     */
    @Override
    public TrainingSchedule updateTrainingSchedule(TrainingSchedule trainingSchedule){
        String sql = "UPDATE \"trainingSchedule\" SET name = ?,  description= ?, \"trainingsIds\" = ? WHERE id = ?";

        try{
            jdbcTemplate.update(sql, preparedStatement -> {
                preparedStatement.setString(1, trainingSchedule.getName());
                preparedStatement.setString(2, trainingSchedule.getDescription());
                preparedStatement.setArray(3, new PostgreSQLInt4Array(trainingSchedule.getTrainingIds()));
            });
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return trainingSchedule;
    }
}
