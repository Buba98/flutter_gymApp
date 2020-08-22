package com.buba.gymApp.backend.model.training;

import java.util.List;
import java.util.UUID;

public class Exercise {
    private final UUID id;
    private final String name;
    private final int reps;
    private final int sets;
    private final int secondForRep;
    private final List<Integer> weights;
    private final String urlVideo;
    private final String gifOrImage;

    public Exercise(UUID id, String name, int reps, int sets, int secondForRep, List<Integer> weights, String urlVideo, String gifOrImage) {
        this.id = id;
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.secondForRep = secondForRep;
        this.weights = weights;
        this.urlVideo = urlVideo;
        this.gifOrImage = gifOrImage;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    public int getSecondForRep() {
        return secondForRep;
    }

    public List<Integer> getWeights() {
        return weights;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public String getGifOrImage() {
        return gifOrImage;
    }
}
