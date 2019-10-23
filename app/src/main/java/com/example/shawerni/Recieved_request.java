package com.example.shawerni;

public class Recieved_request {

    String sender ;
    String msg;
    String idSender;

    public Recieved_request(String sender, String msg, String idSender) {
        this.sender = sender;
        this.msg = msg;
        this.idSender = idSender;
    }

    public String getIdSender() {
        return idSender;
    }


    public String getSender() {
        return sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
