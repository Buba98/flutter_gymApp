package com.buba.gymApp.backend.model.treaningComponents;

import org.springframework.jdbc.core.RowMapper;

public class TrainingSchedule {
    private int id;
    private String name;
    private int[] trainings;
    private String description;

    public TrainingSchedule(int id, String name, int[] trainings, String description) {
        this.id = id;
        this.name = name;
        this.trainings = trainings;
        this.description = description;
    }

    private TrainingSchedule(){}

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

    public int[] getTrainings() {
        return trainings;
    }

    public void setTrainings(int[] trainings) {
        this.trainings = trainings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static RowMapper<TrainingSchedule> mapper(){
        return (resultSet, i) -> {
            TrainingSchedule trainingSchedule = new TrainingSchedule();
            trainingSchedule.id =(resultSet.getInt("id"));
            trainingSchedule.name = resultSet.getString("name");
            trainingSchedule.description = resultSet.getString("description");
            trainingSchedule.trainings = (int[]) resultSet.getArray("trainingsIds").getArray();

            return  trainingSchedule;
        };
    }
}
