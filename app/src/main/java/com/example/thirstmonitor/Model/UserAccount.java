package com.example.thirstmonitor.Model;

public class UserAccount {
    long user_id;
    private String user_name;
    private String password;
    private String full_name;

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long userId) {
        this.user_id = userId;
    }

    public String getUserName() {return user_name;}

    public void setUserName(String userName) {
        this.user_name = userName;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String fullName) {
        this.full_name = fullName;
    }
}
