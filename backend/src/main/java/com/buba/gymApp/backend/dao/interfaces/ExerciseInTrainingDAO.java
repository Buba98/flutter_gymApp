package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.treaningComponents.ExerciseInTraining;

public interface ExerciseInTrainingDAO {
    ExerciseInTraining insertExerciseInTraining(int exerciseId, int[] setIds, String description);

    ExerciseInTraining selectExerciseInTrainingById(int id);

    ExerciseInTraining updateExerciseInTraining(ExerciseInTraining exerciseInTraining);
}
