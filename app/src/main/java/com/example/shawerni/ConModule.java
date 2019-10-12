package com.example.shawerni;


import java.util.HashMap;
import java.util.Map;

public class ConModule {






    private String name;
    private String password;
    private String email;
    private String phoneNum;
    private String id;
    private String major;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getPickedImageUri() {
        return pickedImageUri;
    }

    public void setPickedImageUri(String pickedImageUri) {
        this.pickedImageUri = pickedImageUri;
    }

    private String pickedImageUri ;


    public ConModule(String id , String major , String email, String name, String password, String phoneNum , String path) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.major=major;
        this.id = id;
        this.path = path;
    }

    public ConModule(String name, String password, String email, String phoneNum) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public ConModule() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}