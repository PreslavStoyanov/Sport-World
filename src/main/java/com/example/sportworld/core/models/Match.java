package com.example.sportworld.core.models;

import java.sql.Timestamp;

public class Match {
    public final int id;
    public final String title;
    public final String content;
    public final Timestamp creationDate;
    public final League league;
    public final int user_id;


    public Match(int id, String title, String content, Timestamp creationDate, League league, int user_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.league = league;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", league=" + league +
                ", user_id=" + user_id +
                '}';
    }
}
