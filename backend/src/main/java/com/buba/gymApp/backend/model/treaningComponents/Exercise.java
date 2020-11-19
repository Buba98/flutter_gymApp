package com.buba.gymApp.backend.model.treaningComponents;

import com.google.gson.JsonObject;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Exercise {
    private int id;
    private String name;
    private String urlVideo;
    private String imageOrGif;
    private ExerciseType exerciseType;

    private Exercise() {

    }

    public Exercise(int id, String urlVideo, String imageOrGif, ExerciseType exerciseType, String name) {
        this.id = id;
        this.name = name;
        this.urlVideo = urlVideo;
        this.imageOrGif = imageOrGif;
        this.exerciseType = exerciseType;
    }

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

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getImageOrGif() {
        return imageOrGif;
    }

    public void setImageOrGif(String imageOrGif) {
        this.imageOrGif = imageOrGif;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public static RowMapper<Exercise> mapper(){
        return (resultSet, i) -> {
            Exercise exercise = new Exercise();
            exercise.id = resultSet.getInt("id");
            exercise.name = resultSet.getString("name");
            exercise.urlVideo = resultSet.getString("urlVideo");
            exercise.imageOrGif = resultSet.getString("imageOrGif");
            exercise.exerciseType = ExerciseType.getEnumByInt(resultSet.getInt("typeOfExercise"));

            return  exercise;
        };
    }

    public JsonObject json(){
        JsonObject toReturn = new JsonObject();

        toReturn.addProperty("name", name);
        toReturn.addProperty("id", id);
        toReturn.addProperty("urlVideo", urlVideo);
        toReturn.addProperty("imageOrGif", imageOrGif);
        toReturn.addProperty("typeOfExercise", exerciseType.toString());

        return toReturn;
    }
}