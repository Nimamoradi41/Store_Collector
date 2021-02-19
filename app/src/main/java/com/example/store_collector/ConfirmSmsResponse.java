package com.example.store_collector;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmSmsResponse {

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
    private Data data;

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

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @Expose
        @SerializedName("securityKey")
        private String securityKey;

        @SerializedName("status")
        private int status;

        public String getSecurityKey() {
            return securityKey;
        }

        public void setSecurityKey(String securityKey) {
            this.securityKey = securityKey;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
