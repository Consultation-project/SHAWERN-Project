package com.example.shawerni;


import java.util.Date;

public class Consultation2 {
    String textCons;
    String user_id;
    Date date;

    public Consultation2() {
    }

    public Consultation2(String textCons) {
        this.textCons = textCons;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTextCons() {
        return textCons;
    }

    public void setTextCons(String textCons) {
        this.textCons = textCons;
    }

}