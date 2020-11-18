package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.TrainingDAO;
import com.buba.gymApp.backend.model.treaningComponents.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

@Repository("postgresTraining")
public class TrainingDataAccessService implements TrainingDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TrainingDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert training into DB
     * @param name name of the training
     * @param description description of the training
     * @param exerciseInTrainingIds the ids of the exercisesInTraining
     * @return the new training if it has been created, null otherwise
     */
    @Override
    public Training insertTraining(String name, String description, int[][] exerciseInTrainingIds){
        String sql = "INSERT INTO training (name, description, \"exercisesInTrainingIds\") VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();


        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, description);
                preparedStatement.setArray(3, connection.createArrayOf("integer", exerciseInTrainingIds));
                return preparedStatement;
            }, keyHolder);
            return new Training(Objects.requireNonNull(keyHolder.getKey()).intValue(), name, description, exerciseInTrainingIds);
        } catch (DataAccessException | NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select a training from DB by id
     * @param id id
     * @return the training if it has been found, null otherwise
     */
    @Override
    public Training selectTrainingById(int id){
        String sql = "SELECT * FROM training WHERE id = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), Training.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select a training from DB by name
     * @param name name
     * @return the training if it has been found, null otherwise
     */
    @Override
    public Training selectTrainingByName(String name){
        String sql = "SELECT * FROM training WHERE name = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, name), Training.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update a training into DB
     * @param training the training updated
     * @return the training updated if it has been updated, null otherwise
     */
    @Override
    public Training updateTraining(Training training){
        String sql = "UPDATE training SET name = ?,  description= ?, \"exercisesInTrainingIds\" = ? WHERE id = ?";

        try{
            jdbcTemplate.update(sql, preparedStatement -> {
                preparedStatement.setString(1, training.getName());
                preparedStatement.setString(2, training.getDescription());
                preparedStatement.setArray(3, preparedStatement.getConnection().createArrayOf("integer", training.getExercisesInTraining()));
            });
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return training;
    }
}
