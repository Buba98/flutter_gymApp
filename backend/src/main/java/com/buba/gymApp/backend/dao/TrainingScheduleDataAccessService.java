package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.model.treaningComponents.TrainingSchedule;
import com.buba.gymApp.backend.utils.PostgreSQLInt4Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;

public class TrainingScheduleDataAccessService {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public TrainingScheduleDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public TrainingSchedule insertTraining(String name, String description, int[] trainingsIds){
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
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return new TrainingSchedule(keyHolder.getKey().intValue(), name, trainingsIds, description);
    }

    public TrainingSchedule selectTrainingById(int id){
        String sql = "SELECT * FROM \"trainingSchedule\" WHERE id = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), TrainingSchedule.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    public TrainingSchedule selectTrainingByName(String name){
        String sql = "SELECT * FROM \"trainingSchedule\" WHERE name = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, name), TrainingSchedule.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    public TrainingSchedule updateTraining(TrainingSchedule trainingSchedule){
        String sql = "UPDATE \"trainingSchedule\" SET name = ?,  description= ?, \"trainingsIds\" = ? WHERE id = ?";

        try{
            jdbcTemplate.update(sql, preparedStatement -> {
                preparedStatement.setString(1, trainingSchedule.getName());
                preparedStatement.setString(2, trainingSchedule.getDescription());
                preparedStatement.setArray(3, new PostgreSQLInt4Array(trainingSchedule.getTrainings()));
            });
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return trainingSchedule;
    }
}
