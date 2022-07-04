package com.sportworld.bin.web.api.models;

public class MatchInput {
    public final String title;
    public final String content;
    public final int leagueID;

    public MatchInput(String title, String content, int leagueID) {
        this.title = title;
        this.content = content;
        this.leagueID = leagueID;
    }
}
