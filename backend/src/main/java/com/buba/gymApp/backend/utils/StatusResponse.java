package com.buba.gymApp.backend.utils;

import com.google.gson.annotations.SerializedName;

public class StatusResponse {
    @SerializedName("status")
    private Integer status;
    @SerializedName("token")
    private String token;

    public StatusResponse(int status) {
        this.status = status;
    }
    public StatusResponse(int status, String token) {
        this.status = status;
        this.token = token;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
