package com.buba.gymApp.backend.model.treaningComponents;

public enum ExerciseType {
    ENDURANCE(0),
    STRENGTH(1),
    STRETCHING(2),
    FREE_BODY(3),
    UNKNOWN_TYPE(-1);

    int id;

    ExerciseType(int id) {
        this.id = id;
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
}
