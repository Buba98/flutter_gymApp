package com.buba.gymApp.backend.model.treaningComponents;

public enum ExerciseType {
    ENDURANCE(0, "endurance"),
    STRENGTH(1, "strength"),
    STRETCHING(2, "stretching"),
    FREE_BODY(3, "free body"),
    UNKNOWN_TYPE(-1, "unknown");

    int id;
    String name;

    ExerciseType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public static ExerciseType getEnumByInt(int type) {
        switch (type) {
            case 0:
                return ENDURANCE;
            case 1:
                return STRENGTH;
            case 2:
                return STRETCHING;
            case 3:
                return FREE_BODY;
            default:
                return UNKNOWN_TYPE;
        }

    }

    @Override
    public String toString(){
        return name;
    }
}
