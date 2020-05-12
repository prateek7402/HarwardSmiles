package com.example.harwardsmiles.DataSets;

public class DataSet_Users {
    public DataSet_Users(String name, String email, String mobile, String address, String state, String gender, String dob, String user_auth_id, int user_registration_number) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.state = state;
        this.gender = gender;
        this.dob = dob;
        this.user_auth_id = user_auth_id;
        this.user_registration_number = user_registration_number;
    }

    String name;
    String email;
    String mobile;
    String address;
    String state;
    String gender;
    String dob;
    String user_auth_id;
    int user_registration_number;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUser_auth_id() {
        return user_auth_id;
    }

    public void setUser_auth_id(String user_auth_id) {
        this.user_auth_id = user_auth_id;
    }

    public int getUser_registration_number() {
        return user_registration_number;
    }

    public void setUser_registration_number(int user_registration_number) {
        this.user_registration_number = user_registration_number;
    }

    public DataSet_Users() {
    }
}
