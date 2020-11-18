package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.ExerciseDAO;
import com.buba.gymApp.backend.model.treaningComponents.Exercise;
import com.buba.gymApp.backend.model.treaningComponents.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository("postgresExercise")
public class ExerciseDataAccessService implements ExerciseDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExerciseDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert an exercise
     * @param name the name of exercise
     * @param urlVideo the url of the exercise
     * @param imageOrGif the image or gif of the exercise
     * @param exerciseType the type of exercise
     * @return if the exercise has been created successfully returns the exercise otherwise null
     */
    public Exercise insertExercise(String name, String urlVideo, String imageOrGif, ExerciseType exerciseType){
        String sql = "INSERT INTO exercise (\"urlVideo\", \"imageOrGif\", \"typeOfExercise\", \"name\") VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, urlVideo);
                preparedStatement.setString(2, imageOrGif);
                preparedStatement.setInt(3, exerciseType.getId());
                preparedStatement.setString(4, name);
                return preparedStatement;
            }, keyHolder);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }

        return new Exercise(keyHolder.getKey().intValue(), urlVideo, imageOrGif, exerciseType, name);
    }

    /**
     * Get the exercise by name
     * @param id id of exercise
     * @return if exist the exercise, otherwise null
     */
    public Exercise selectExerciseById(int id){
        String sql = "SELECT * FROM exercise WHERE id = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), Exercise.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the exercise by name
     * @param name name of exercise
     * @return if exist the exercise, otherwise null
     */
    public Exercise selectExerciseByName(String name){
        String sql = "SELECT * FROM exercise WHERE name = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, name), Exercise.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update the exercise into the database
     * @param exercise is the exercise to updated. The id has to be the same
     * @return the updated exercise if the update has been completed successfully, null otherwise
     */
    public Exercise updateExercise(Exercise exercise){
        String sql = "UPDATE exercise SET name = ?, \"urlVideo\" = ?, \"imageOrGif\" = ?, \"typeOfExercise\" = ? WHERE id = ?";
        try{
            jdbcTemplate.update(sql, preparedStatement -> {
                preparedStatement.setString(1, exercise.getName());
                preparedStatement.setString(2, exercise.getUrlVideo());
                preparedStatement.setString(3, exercise.getImageOrGif());
                preparedStatement.setInt(4, exercise.getExerciseType().getId());
                preparedStatement.setInt(5, exercise.getId());
            });
            return  exercise;
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
