package com.buba.gymApp.backend.utils;

public enum RequestType {
    SIGN_UP(0),
    INSURANCE_PAYMENT(1),
    SUBSCRIPTION_PAYMENT(2),
    ENTRANCE(3),
    NEW_SUBSCRIPTION_TYPE(4),
    AUTOCOMPLETE(5),
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
            case 4: return NEW_SUBSCRIPTION_TYPE;
            case 5: return AUTOCOMPLETE;
            default: return BAD_REQUEST;
        }
    }
}
