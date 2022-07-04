package com.sportworld.core.models;

public class MailToken {
    public int id;
    public String token;
    public int userID;

    public MailToken() {
    }

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
