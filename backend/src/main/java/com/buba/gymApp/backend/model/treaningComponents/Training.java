package com.buba.gymApp.backend.model.treaningComponents;

import org.springframework.jdbc.core.RowMapper;

public class Training {
    private int id;
    private String name;
    private String description;
    private int[][] exercisesInTraining;

    public Training(int id, String name, String description, int[][] exercisesInTraining) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exercisesInTraining = exercisesInTraining;
    }

    private Training(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[][] getExercisesInTraining() {
        return exercisesInTraining;
    }

    public void setExercisesInTraining(int[][] exercisesInTraining) {
        this.exercisesInTraining = exercisesInTraining;
    }

    public static RowMapper<Training> mapper(){
        return (resultSet, i) -> {
            Training training = new Training();
            training.id =(resultSet.getInt("id"));
            training.name = resultSet.getString("name");
            training.description = resultSet.getString("description");
            training.exercisesInTraining = (int[][]) resultSet.getArray("exercisesInTrainingIds").getArray();

            return  training;
        };
    }
}
