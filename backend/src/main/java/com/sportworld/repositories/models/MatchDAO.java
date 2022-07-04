package com.sportworld.repositories.models;

import java.sql.Timestamp;

public final class MatchDAO {
    public final int id;
    public final String title;
    public final String content;
    public final Timestamp creationDate;
    public final LeagueDAO league;
    public final int userID;

    public MatchDAO(int id, String title, String content, Timestamp creationDate, LeagueDAO league, int userID) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.league = league;
        this.userID = userID;
    }
}
