package com.example.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data {
    @Expose
    @SerializedName("token")
    private String token;
    @Expose
    @SerializedName("securityKey")
    String securityKey;
    @Expose
    @SerializedName("age")
    private int age;

    public com.example.Models.appVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(com.example.Models.appVersion appVersion) {
        this.appVersion = appVersion;
    }

    @Expose
    @SerializedName("appVersion")
    private appVersion appVersion;


    @Expose
    @SerializedName("gender")
    private int gender;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Expose
    @SerializedName("type")
    private int type;





    @Expose
    @SerializedName("fullName")
    private String fullName;


    @Expose
    @SerializedName("profileImage")
    private String profileImage;





    @Expose
    @SerializedName("birthDayFa")
    private String birthDayFa;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBirthDayFa() {
        return birthDayFa;
    }

    public void setBirthDayFa(String birthDayFa) {
        this.birthDayFa = birthDayFa;
    }




}
