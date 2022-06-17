package com.example.sportworld.web.api.models;

public class CommentInput {
    public String content;
    public int matchID;

    public CommentInput(String content, int matchID) {
        this.content = content;
        this.matchID = matchID;
    }
}
