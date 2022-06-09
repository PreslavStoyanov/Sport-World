package web_project.repositories.models;

import java.sql.Timestamp;

public record CommentDAO(int id, String content, int votes, int views, Timestamp creationDate, UserDAO user,
                         PostDAO post) {
}
