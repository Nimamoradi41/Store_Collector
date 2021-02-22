package com.example.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class appVersion {
    @Expose
    @SerializedName("currentVersion")
    int currentVersion;

    @Expose
    @SerializedName("allowedToLogin")
    Boolean allowedToLogin;


    @Expose
    @SerializedName("url")
    String url;

    @Expose
    @SerializedName("description")
    String description;

    public int getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Boolean getAllowedToLogin() {
        return allowedToLogin;
    }

    public void setAllowedToLogin(Boolean allowedToLogin) {
        this.allowedToLogin = allowedToLogin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
