package com.buba.gymApp.backend.model.administrationComponents;

public class Subscription {
    private int id;
    private int mouthDuration;
    private float cost;
    private int maxEntrances;

    public Subscription(Integer id, int mouthDuration, float cost, int maxEntrances) {
        this.id = id;
        this.mouthDuration = mouthDuration;
        this.cost = cost;
        this.maxEntrances = maxEntrances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMouthDuration() {
        return mouthDuration;
    }

    public void setMouthDuration(int mouthDuration) {
        this.mouthDuration = mouthDuration;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getMaxEntrances() {
        return maxEntrances;
    }

    public void setMaxEntrances(int maxEntrances) {
        this.maxEntrances = maxEntrances;
    }
}
