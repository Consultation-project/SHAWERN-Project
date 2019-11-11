package com.example.shawerni;

public class anQ {

    String answer , sender , reciver , msg ;

    public anQ(){}

    public anQ(String answer, String sender, String reciver, String msg) {
        this.answer = answer;
        this.sender = sender;
        this.reciver = reciver;
        this.msg = msg;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSender() {
        return sender;
    }

    public String getReciver() {
        return reciver;
    }

    public String getMsg() {
        return msg;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
