package com.buba.gymApp.backend.model.treaningComponents;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.postgresql.util.PGInterval;
import org.springframework.jdbc.core.RowMapper;

import java.time.Duration;

public class Set {
    private int id;
    private int[] reps;
    private Duration rest;
    private Duration eccentricDuration;
    private Duration concentricDuration;
    private Duration setDuration;

    public Set(int id, int[] reps, Duration rest, Duration eccentricDuration, Duration concentricDuration, Duration setDuration) {
        this.id = id;
        this.reps = reps;
        this.rest = rest;
        this.eccentricDuration = eccentricDuration;
        this.concentricDuration = concentricDuration;
        this.setDuration = setDuration;
    }

    private Set(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getReps() {
        return reps;
    }

    public void setReps(int[] reps) {
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

    public static RowMapper<Set> mapper(){
        return (resultSet, i) -> {
            Set set = new Set();
            set.id = resultSet.getInt("id");
            set.rest = Duration.ofSeconds((long) resultSet.getObject("rest", PGInterval.class).getSeconds());
            set.eccentricDuration = Duration.ofSeconds((long) resultSet.getObject("eccentricDuration", PGInterval.class).getSeconds());
            set.concentricDuration = Duration.ofSeconds((long) resultSet.getObject("concentricDuration", PGInterval.class).getSeconds());
            set.setDuration = Duration.ofSeconds((long) resultSet.getObject("setDuration", PGInterval.class).getSeconds());
            set.reps = (int[]) resultSet.getArray("reps").getArray();


            return  set;
        };
    }

    public JsonObject json(){
        JsonObject toReturn = new JsonObject();

        toReturn.addProperty("id", id);
        toReturn.addProperty("rest", rest.toSeconds());
        toReturn.addProperty("eccentricDuration", eccentricDuration.toSeconds());
        toReturn.addProperty("concentricDuration", concentricDuration.toSeconds());
        toReturn.addProperty("setDuration", setDuration.toSeconds());

        JsonArray jsonArray = new JsonArray();

        for (int rep : reps){
            jsonArray.add(rep);
        }
        toReturn.add("reps", jsonArray);

        return toReturn;
    }

}
