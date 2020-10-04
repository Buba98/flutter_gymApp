package com.buba.gymApp.backend.model.treaningComponents;

import java.util.List;

public class Training {
    private int id;
    private String name;
    private String description;
    private List<List<Integer>> exercisesInTraining;

    public Training(int id, String name, String description, List<List<Integer>> exercisesInTraining) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exercisesInTraining = exercisesInTraining;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<List<Integer>> getExercisesInTraining() {
        return exercisesInTraining;
    }

    public void setExercisesInTraining(List<List<Integer>> exercisesInTraining) {
        this.exercisesInTraining = exercisesInTraining;
    }
}
