package com.buba.gymApp.backend.utils;

public enum RequestType {
    SIGN_UP(0),
    INSURANCE_PAYMENT(1),
    SUBSCRIPTION_PAYMENT(2),
    ENTRANCE(3),
    BAD_REQUEST(-1);

    int type;

    RequestType(int type){
        this.type = type;
    }

    public static RequestType getEnumByInt(int type){
        switch (type){
            case 0: return SIGN_UP;
            case 1: return INSURANCE_PAYMENT;
            case 2: return SUBSCRIPTION_PAYMENT;
            case 3: return ENTRANCE;
            default: return BAD_REQUEST;
        }
    }
}
