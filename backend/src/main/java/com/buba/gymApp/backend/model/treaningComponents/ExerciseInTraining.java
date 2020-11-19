package com.buba.gymApp.backend.model.treaningComponents;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.jdbc.core.RowMapper;

public class ExerciseInTraining {
    private int id;
    private int exerciseId;
    private int[] setsIds;
    private String description;

    public ExerciseInTraining(int id, int exerciseId, int[] setsIds, String description) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.setsIds = setsIds;
        this.description = description;
    }

    private ExerciseInTraining(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int[] getSetsIds() {
        return setsIds;
    }

    public void setSetsIds(int[] setsIds) {
        this.setsIds = setsIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static RowMapper<ExerciseInTraining> mapper(){
        return (resultSet, i) -> {
            ExerciseInTraining exerciseInTraining = new ExerciseInTraining();
            exerciseInTraining.id = resultSet.getInt("id");
            exerciseInTraining.exerciseId = resultSet.getInt("exerciseId");
            exerciseInTraining.setsIds = (int[]) resultSet.getArray("setsIds").getArray();
            exerciseInTraining.description = resultSet.getString("description");

            return  exerciseInTraining;
        };
    }

    public JsonObject json(){
        JsonObject toReturn = new JsonObject();

        toReturn.addProperty("id", id);
        toReturn.addProperty("exerciseId", exerciseId);

        JsonArray jsonArray = new JsonArray();

        for(int setId : setsIds){
            jsonArray.add(setId);
        }

        toReturn.add("setIds", jsonArray);

        return toReturn;
    }
}
