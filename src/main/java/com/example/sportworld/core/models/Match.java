package com.example.sportworld.core.models;

import java.sql.Timestamp;

public class Match {
    public final int id;
    public final String title;
    public final String content;
    public final Timestamp creationDate;
    public final League league;

    public Match(int id, String title, String content, Timestamp creationDate, League league) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.league = league;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", league=" + league +
                '}';
    }
}
