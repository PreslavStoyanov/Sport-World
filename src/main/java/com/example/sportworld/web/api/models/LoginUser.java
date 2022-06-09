package com.example.sportworld.web.api.models;

public class LoginUser {
    public final String username;
    public final String token;

    public LoginUser(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
