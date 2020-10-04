package com.buba.gymApp.backend.model.treaningComponents;

import java.util.List;

public class ExerciseInTraining {
    private int id;
    private int idExercise;
    private List<Integer> sets;
    private String description;

    public ExerciseInTraining(int id, int idExercise, List<Integer> sets, String description) {
        this.id = id;
        this.idExercise = idExercise;
        this.sets = sets;
        this.description = description;
    }

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

    public List<Integer> getSets() {
        return sets;
    }

    public void setSets(List<Integer> sets) {
        this.sets = sets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
