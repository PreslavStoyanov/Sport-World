package com.sportworld.core.models;

import java.sql.Timestamp;

public class Comment {
    public int id;
    public String content;
    public Timestamp creationDate;
    public int userID;
    public int matchID;
    public String author;

    public Comment() {
    }

    public Comment(int id, String content, Timestamp creationDate, int userID, int matchID) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.userID = userID;
        this.matchID = matchID;
    }
}
