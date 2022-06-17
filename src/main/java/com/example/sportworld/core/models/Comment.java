package com.example.sportworld.core.models;

import java.sql.Timestamp;

public class Comment {
    public final int id;
    public final String content;
    public final Timestamp creationDate;
    public final int userID;
    public final int matchID;
    public String author;

    public Comment(int id, String content, Timestamp creationDate, int userID, int matchID) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.userID = userID;
        this.matchID = matchID;
    }
}
