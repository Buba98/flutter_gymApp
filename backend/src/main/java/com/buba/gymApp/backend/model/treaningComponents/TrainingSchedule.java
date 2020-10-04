package com.buba.gymApp.backend.model.treaningComponents;

import java.util.List;

public class TrainingSchedule {
    private int id;
    private String name;
    private List<Integer> trainings;
    private String description;

    public TrainingSchedule(int id, String name, List<Integer> trainings, String description) {
        this.id = id;
        this.name = name;
        this.trainings = trainings;
        this.description = description;
    }

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

    public List<Integer> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Integer> trainings) {
        this.trainings = trainings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
