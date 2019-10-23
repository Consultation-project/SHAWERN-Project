package com.example.shawerni;

import java.util.HashMap;
import java.util.Map;

public class Module {
    private String name,password,email,phoneNum,age,id ,msg;


    public Module(String id ,String age , String email, String name, String password, String phoneNum, String msg) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.age=age;
        this.id = id;
        this.msg = msg;


    }

    public Module(String name, String password, String email, String phoneNum) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Module() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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



    public String getAge() {
        return age;
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

    public void setAge(String age) {
        this.age = age;
    }
}
