package com.buba.gymApp.backend.model.treaningComponents;

import com.buba.gymApp.backend.utils.PostgreSQLInt4Array;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class ExerciseInTraining {
    private int id;
    private int idExercise;
    private int[] sets;
    private String description;

    public ExerciseInTraining(int id, int idExercise, int[] sets, String description) {
        this.id = id;
        this.idExercise = idExercise;
        this.sets = sets;
        this.description = description;
    }

    private ExerciseInTraining(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }

    public int[] getSets() {
        return sets;
    }

    public void setSets(int[] sets) {
        this.sets = sets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static RowMapper<ExerciseInTraining> mapper(){
        return (resultSet, i) -> {
            ExerciseInTraining exerciseInTraining = new ExerciseInTraining();
            exerciseInTraining.id = resultSet.getInt("id");
            exerciseInTraining.idExercise = resultSet.getInt("exerciseId");
            exerciseInTraining.sets = (int[]) resultSet.getArray("setsIds").getArray();
            exerciseInTraining.description = resultSet.getString("description");

            return  exerciseInTraining;
        };
    }
}
