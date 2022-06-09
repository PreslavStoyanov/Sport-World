package web_project.web.api.models;

import web_project.repositories.models.PostDAO;
import web_project.repositories.models.UserDAO;

import java.sql.Timestamp;

public record CommentInput(int id, String content, int votes, int views, Timestamp creationDate, UserDAO user,
                           PostDAO post) {
}
