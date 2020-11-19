package com.buba.gymApp.backend.model.treaningComponents;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.jdbc.core.RowMapper;

public class TrainingSchedule {
    private int id;
    private String name;
    private int[] trainingIds;
    private String description;

    public TrainingSchedule(int id, String name, int[] trainingIds, String description) {
        this.id = id;
        this.name = name;
        this.trainingIds = trainingIds;
        this.description = description;
    }

    private TrainingSchedule(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getTrainingIds() {
        return trainingIds;
    }

    public void setTrainingIds(int[] trainingIds) {
        this.trainingIds = trainingIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static RowMapper<TrainingSchedule> mapper(){
        return (resultSet, i) -> {
            TrainingSchedule trainingSchedule = new TrainingSchedule();
            trainingSchedule.id =(resultSet.getInt("id"));
            trainingSchedule.name = resultSet.getString("name");
            trainingSchedule.description = resultSet.getString("description");
            trainingSchedule.trainingIds = (int[]) resultSet.getArray("trainingsIds").getArray();

            return  trainingSchedule;
        };
    }

    public JsonObject json(){
        JsonObject toReturn = new JsonObject();

        toReturn.addProperty("id", id);
        toReturn.addProperty("name", name);
        toReturn.addProperty("description", description);

        JsonArray jsonArray = new JsonArray();

        for (int trainingId : trainingIds){
            jsonArray.add(trainingId);
        }

        toReturn.add("trainingIds", jsonArray);

        return toReturn;
    }
}
