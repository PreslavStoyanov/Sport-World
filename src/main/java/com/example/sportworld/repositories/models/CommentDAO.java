package com.example.sportworld.repositories.models;

import java.sql.Timestamp;

public class CommentDAO {
    public final int id;
    public final String content;
    public final Timestamp creationDate;
    public final int userID;
    public final int matchID;

    public CommentDAO(int id, String content, Timestamp creationDate, int userID, int matchID) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.userID = userID;
        this.matchID = matchID;
    }
}
