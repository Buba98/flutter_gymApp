package com.buba.gymApp.backend.model.person;

import com.buba.gymApp.backend.model.Entry;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class StandardUser extends Person {

    private final List<Date> indemnities;
    private final List<Entry> entries;
    private final List<UUID> trainingSchedules;

    public StandardUser(UUID id, String name, String surname, String cellphone, Date birthday, String email, List<Date> indemnities, List<Entry> entries, String password, List<UUID> trainingSchedules) {
        super(id, name, surname, cellphone, birthday, email, password);
        this.indemnities = indemnities;
        this.entries = entries;
        this.trainingSchedules = trainingSchedules;
    }

    public List<Date> getIndemnities() {
        return indemnities;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public List<UUID> getTrainingSchedules() {
        return trainingSchedules;
    }
}