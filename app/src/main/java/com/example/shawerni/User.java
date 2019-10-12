package com.example.shawerni;

import android.widget.Button;
import android.widget.EditText;

public class User {

    String NName;
    String password;
    String email;
    String PhoneNum;
    String age;


    public User(){}


    public User(String nname, String password, String email, String phoneNum, String age) {
        NName = nname;
        this.password = password;
        this.email = email;
        PhoneNum = phoneNum;
        this.age = age;
    }

    public void setNName(String nname) {
        NName = nname;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNName() {
        return NName;
    }

    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public String getAge() {
        return age;
    }
}
