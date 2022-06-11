package com.example.sportworld.web.api.models;

public class UserLogin {
    public final String userID;
    public final String token;

    public UserLogin(String userID, String token) {
        this.userID = userID;
        this.token = token;
    }
}
