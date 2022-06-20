package com.example.sportworld.core.models;

public class MailToken {
    public final int id;
    public final String token;
    public final int userID;

    public MailToken(int id, String token, int userID) {
        this.id = id;
        this.token = token;
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "MailToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", userID=" + userID +
                '}';
    }
}
