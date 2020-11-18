package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.treaningComponents.TrainingSchedule;

public interface TrainingScheduleDAO {
    TrainingSchedule insertTrainingSchedule(String name, String description, int[] trainingsIds);

    TrainingSchedule selectTrainingScheduleById(int id);

    TrainingSchedule selectTrainingScheduleByName(String name);

    TrainingSchedule updateTrainingSchedule(TrainingSchedule trainingSchedule);
}
