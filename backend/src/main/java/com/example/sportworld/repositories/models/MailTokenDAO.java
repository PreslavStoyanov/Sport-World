package com.example.sportworld.repositories.models;

public class MailTokenDAO {
    public final int id;
    public final String token;
    public final int userID;

    public MailTokenDAO(int id, String token, int userID) {
        this.id = id;
        this.token = token;
        this.userID = userID;
    }
}
