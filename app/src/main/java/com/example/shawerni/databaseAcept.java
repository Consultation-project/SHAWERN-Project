package com.example.shawerni;

public class databaseAcept {
    String acceptname;
    String email;
    String major;
    String phone;

    public databaseAcept(String acceptname, String email, String major, String phone) {
        this.acceptname = acceptname;
        this.email = email;
        this.major = major;
        this.phone = phone;
    }

    public databaseAcept() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAcceptname() {
        return acceptname;
    }

    public void setAcceptname(String acceptname) {
        this.acceptname = acceptname;
    }
}
