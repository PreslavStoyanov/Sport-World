package com.sportworld.bin.web.api.models;

public class LoginInput {
    public final String userID;
    public final String token;

    public LoginInput(String userID, String token) {
        this.userID = userID;
        this.token = token;
    }
}
