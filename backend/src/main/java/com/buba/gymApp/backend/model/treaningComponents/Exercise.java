package com.buba.gymApp.backend.model.treaningComponents;

public class Exercise {
    private int id;
    private String urlVideo;
    private String imageOrGif;
    private ExerciseType exerciseType;

    public Exercise(int id, String urlVideo, String imageOrGif, ExerciseType exerciseType) {
        this.id = id;
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
}
