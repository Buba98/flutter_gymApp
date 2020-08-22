package com.buba.gymApp.backend.model;

import java.util.Date;

public class Entry {
    private final Date startingDate;
    private final Date expirationDate;
    private final int Days;

    public Entry(Date startingDate, Date expirationDate, int days) {
        this.startingDate = startingDate;
        this.expirationDate = expirationDate;
        Days = days;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getDays() {
        return Days;
    }
}
