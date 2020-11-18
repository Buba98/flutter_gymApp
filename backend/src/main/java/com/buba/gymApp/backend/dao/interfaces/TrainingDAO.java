package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.treaningComponents.Training;

public interface TrainingDAO {
    Training insertTraining(String name, String description, int[][] exerciseInTrainingIds);

    Training selectTrainingById(int id);

    Training selectTrainingByName(String name);

    Training updateTraining(Training training);
}
