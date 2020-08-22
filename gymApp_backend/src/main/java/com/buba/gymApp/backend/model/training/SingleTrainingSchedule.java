package com.buba.gymApp.backend.model.training;

import java.util.List;
import java.util.UUID;

public class SingleTrainingSchedule {
    private final UUID id;
    private final List<Exercise> exercises;
    private final String name;
    private final String description;

    public SingleTrainingSchedule(UUID id, List<Exercise> exercises, String name, String description) {
        this.id = id;
        this.exercises = exercises;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
