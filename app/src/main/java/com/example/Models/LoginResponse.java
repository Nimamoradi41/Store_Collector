package com.example.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("statusCode")
    private int statusCode;
    @Expose
    @SerializedName("isSuccess")
    private boolean isSuccess;


    @Expose
    @SerializedName("data")
    public data data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public com.example.Models.data getData() {
        return data;
    }

    public void setData(com.example.Models.data data) {
        this.data = data;
    }
}

