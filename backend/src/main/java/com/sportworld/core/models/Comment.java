package com.sportworld.core.models;

import java.sql.Timestamp;

public class Comment {
    private int id;
    private String content;
    private Timestamp creationDate;
    private int userID;
    private int matchID;
    private String author;

    public Comment() {
    }

    public Comment(int id, String content, Timestamp creationDate, int userID, int matchID) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.userID = userID;
        this.matchID = matchID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
