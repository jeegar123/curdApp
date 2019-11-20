package com.app.advancecurd.adapters;

public class Item {

    String username;
    String fullname;
    String gender;
    String city;
    String status;
    String branch;


    public Item(String fullname,String username, String gender, String city, String status, String branch) {
        this.username = username;
        this.fullname = fullname;
        this.gender = gender;
        this.city = city;
        this.status = status;
        this.branch = branch;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
