package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.model.treaningComponents.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository("postgresTraining")
public class TrainingDataAccessService {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TrainingDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

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
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return new Training(keyHolder.getKey().intValue(), name, description, exerciseInTrainingIds);
    }

    public Training selectTrainingById(int id){
        String sql = "SELECT * FROM training WHERE id = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), Training.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    public Training selectTrainingByName(String name){
        String sql = "SELECT * FROM training WHERE name = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, name), Training.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

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
