package com.buba.gymApp.backend.model.treaningComponents;

public enum ExerciseType {
    ENDURANCE(0),
    STRENGTH(1),
    STRETCHING(2),
    FREE_BODY(3);

    int id;

    ExerciseType(int id){
        this.id = id;
    }
}
