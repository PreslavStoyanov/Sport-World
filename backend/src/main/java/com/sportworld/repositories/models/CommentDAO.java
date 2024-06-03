package com.sportworld.repositories.models;

import java.sql.Timestamp;

public record CommentDAO(int id, String content, Timestamp creationDate, int userID, int matchID) {
}
