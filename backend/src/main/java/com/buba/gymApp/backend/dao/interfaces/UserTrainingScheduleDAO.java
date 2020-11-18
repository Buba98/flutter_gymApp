package com.buba.gymApp.backend.dao.interfaces;

import com.buba.gymApp.backend.model.treaningComponents.UserTrainingSchedule;

import java.util.Date;
import java.util.List;

public interface UserTrainingScheduleDAO {
    UserTrainingSchedule insertUserTrainingSchedule(int userId, int trainingScheduleId, Date startDate, Date endDate, String comment);

    UserTrainingSchedule selectUserTrainingScheduleById(int id);

    List<UserTrainingSchedule> selectUserTrainingScheduleByUserId(int userId);

    List<UserTrainingSchedule> selectNotExpiredUserTrainingScheduleByUserId(int userId);

    UserTrainingSchedule updateUserTrainingSchedule(UserTrainingSchedule userTrainingSchedule);
}
