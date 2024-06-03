package com.sportworld.core.models;

import java.sql.Timestamp;

public record Match(int id, String title, String content, Timestamp creationDate, League league, int userId) {
}
