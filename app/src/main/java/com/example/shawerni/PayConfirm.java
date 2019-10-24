package com.example.shawerni;

import java.util.HashMap;
import java.util.Map;

public class PayConfirm {

    private String id;
    private String name;
    private String email;
    private String consultentName;
    private String date;
    private String time;
    private String type;


    public PayConfirm(String id , String name, String email,String consultentName , String date, String time , String type ) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.consultentName = consultentName;
        this.date = date;
        this.time= time;
        this.type = type;
    }


    public PayConfirm(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConsultentName() {
        return consultentName;
    }

    public void setConsultentName(String consultentName) {
        this.consultentName = consultentName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

