package com.example.shawerni;

public class PayConfirm {

    private String id;
    private String name;
    private String email;
    private String consultentName;
    private String date;
    private String time;
    private String type;



    private String url;


    public PayConfirm(String id , String name, String email,String consultentName , String date, String time , String type ,String url) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.consultentName = consultentName;
        this.date = date;
        this.time= time;
        this.type = type;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}