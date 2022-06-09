package web_project.repositories.models;

import java.sql.Timestamp;
import java.util.List;

public record PostDAO(int id, String title, String content, int votes, int views, Timestamp creationDate,
                      String category, List<String> tags, UserDAO user) {
}
