package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.model.treaningComponents.ExerciseInTraining;
import com.buba.gymApp.backend.utils.PostgreSQLInt4Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository("postgresExerciseInTraining")
public class ExerciseInTrainingDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExerciseInTrainingDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert an exercise
     * @param exerciseId the id of the exercise
     * @param setIds the ids of the sets
     * @param description the description of the exercise
     * @return if the exerciseInTraining has been created successfully returns the exercise otherwise null
     */
    public ExerciseInTraining insertExerciseInTraining(int exerciseId, int[] setIds, String description){
        String sql = "INSERT INTO \"exerciseInTraining\" (\"exerciseId\", \"setsIds\", \"description\") VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setInt(1, exerciseId);
                preparedStatement.setArray(2, new PostgreSQLInt4Array(setIds));
                preparedStatement.setString(3, description);
                return preparedStatement;
            }, keyHolder);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }

        return new ExerciseInTraining(keyHolder.getKey().intValue(), exerciseId, setIds, description);
    }

    /**
     * Get the exerciseInTraining by name
     * @param id id of exercise
     * @return if exist the exercise, otherwise null
     */
    public ExerciseInTraining selectExerciseInTrainingById(int id){
        String sql = "SELECT * FROM \"exerciseInTraining\" WHERE id = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), ExerciseInTraining.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update the exerciseInTraining into the database
     * @param exerciseInTraining is the exerciseInTraining to updated. The id has to be the same
     * @return the updated exerciseInTraining if the update has been completed successfully, null otherwise
     */
    public ExerciseInTraining updateExerciseInTraining(ExerciseInTraining exerciseInTraining){
        String sql = "UPDATE \"exerciseInTraining\" SET \"exerciseId\" = ?, \"setsIds\" = ?, description = ? WHERE id = ?";
        try{
            jdbcTemplate.update(sql, preparedStatement -> {
                preparedStatement.setInt(1, exerciseInTraining.getIdExercise());
                preparedStatement.setArray(2, new PostgreSQLInt4Array(exerciseInTraining.getSets()));
                preparedStatement.setString(3, exerciseInTraining.getDescription());
            });
            return  exerciseInTraining;
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
