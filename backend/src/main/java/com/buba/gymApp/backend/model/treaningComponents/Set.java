package com.buba.gymApp.backend.model.treaningComponents;

import java.time.Duration;
import java.util.List;

public class Set {
    private int id;
    private List<Integer> reps;
    private Duration rest;
    private Duration eccentricDuration;
    private Duration concentricDuration;
    private Duration setDuration;

    public Set(int id, List<Integer> reps, Duration rest, Duration eccentricDuration, Duration concentricDuration, Duration setDuration) {
        this.id = id;
        this.reps = reps;
        this.rest = rest;
        this.eccentricDuration = eccentricDuration;
        this.concentricDuration = concentricDuration;
        this.setDuration = setDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getReps() {
        return reps;
    }

    public void setReps(List<Integer> reps) {
        this.reps = reps;
    }

    public Duration getRest() {
        return rest;
    }

    public void setRest(Duration rest) {
        this.rest = rest;
    }

    public Duration getEccentricDuration() {
        return eccentricDuration;
    }

    public void setEccentricDuration(Duration eccentricDuration) {
        this.eccentricDuration = eccentricDuration;
    }

    public Duration getConcentricDuration() {
        return concentricDuration;
    }

    public void setConcentricDuration(Duration concentricDuration) {
        this.concentricDuration = concentricDuration;
    }

    public Duration getSetDuration() {
        return setDuration;
    }

    public void setSetDuration(Duration setDuration) {
        this.setDuration = setDuration;
    }
}
