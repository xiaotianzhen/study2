package com.qianwang.mvplogin2.model;

/**
 * Created by sky on 2017/4/19.
 */

public class User {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int checkUserValidity(String username, String password) {
        if (username == null || password == null ||
                username.isEmpty() ||
                password.isEmpty()) {
            return -1;
        }
        return 0;
    }
}
