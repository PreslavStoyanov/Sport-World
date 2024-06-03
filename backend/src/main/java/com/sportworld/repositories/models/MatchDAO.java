package com.sportworld.repositories.models;

import java.sql.Timestamp;

public record MatchDAO(int id, String title, String content, Timestamp creationDate, LeagueDAO league, int userID) {
}
