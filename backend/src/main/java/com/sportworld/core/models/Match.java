package com.sportworld.core.models;

import java.sql.Timestamp;

public class Match {
    public int id;
    public String title;
    public String content;
    public Timestamp creationDate;
    public League league;
    public int user_id;

    public Match() {
    }

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
