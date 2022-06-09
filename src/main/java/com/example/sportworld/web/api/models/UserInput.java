package com.example.sportworld.web.api.models;

public class UserInput {
    public final String username;
    public final String password;
    public final String email;
    public final String phoneNumber;


    public UserInput(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
