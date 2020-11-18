package com.buba.gymApp.backend.model.administrationComponents;

import org.springframework.jdbc.core.RowMapper;

public class Subscription {
    private int id;
    private String name;
    private int mouthDuration;
    private float cost;
    private int maxEntrances;

    public Subscription(int id, String name, int mouthDuration, float cost, int maxEntrances) {
        this.id = id;
        this.name = name;
        this.mouthDuration = mouthDuration;
        this.cost = cost;
        this.maxEntrances = maxEntrances;
    }

    private Subscription(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public static RowMapper<Subscription> mapper(){
        return (resultSet, i) -> {
            Subscription subscription = new Subscription();

            subscription.id = resultSet.getInt("id");
            subscription.cost = resultSet.getFloat("cost");
            subscription.maxEntrances = resultSet.getInt("maxEntrances");
            subscription.mouthDuration = resultSet.getInt("mouthDuration");
            subscription.name = resultSet.getString("name");

            return subscription;
        };
    }
}
