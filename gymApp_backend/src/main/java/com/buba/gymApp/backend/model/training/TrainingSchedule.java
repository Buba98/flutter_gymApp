package com.buba.gymApp.backend.model.training;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TrainingSchedule {
    private final UUID id;
    private final List<SingleTrainingSchedule> singleTrainingScheduleList;
    private final String name;
    private final String description;
    private final Date expirationDate;
    private final Date startingDate;

    public TrainingSchedule(UUID id, List<SingleTrainingSchedule> singleTrainingScheduleList, String name, String description, Date expirationDate, Date startingDate) {
        this.id = id;
        this.singleTrainingScheduleList = singleTrainingScheduleList;
        this.name = name;
        this.description = description;
        this.expirationDate = expirationDate;
        this.startingDate = startingDate;
    }

    public UUID getId() {
        return id;
    }

    public List<SingleTrainingSchedule> getSingleTrainingScheduleList() {
        return singleTrainingScheduleList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getStartingDate() {
        return startingDate;
    }
}
