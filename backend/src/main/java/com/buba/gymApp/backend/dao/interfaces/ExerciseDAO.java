package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.treaningComponents.Exercise;
import com.buba.gymApp.backend.model.treaningComponents.ExerciseType;

public interface ExerciseDAO {
    Exercise insertExercise(String name, String urlVideo, String imageOrGif, ExerciseType exerciseType);

    Exercise selectExerciseById(int id);

    Exercise selectExerciseByName(String name);

    Exercise updateExercise(Exercise exercise);
}
