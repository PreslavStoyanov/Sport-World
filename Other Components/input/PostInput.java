package web_project.web.api.models;

import web_project.repositories.models.UserDAO;

import java.sql.Timestamp;
import java.util.List;

public record PostInput(int id, String title, String content, int votes, int views, Timestamp creationDate,
                        String category, List<String> tags, UserDAO user) {
}
