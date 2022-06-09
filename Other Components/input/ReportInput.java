package web_project.web.api.models;

import web_project.repositories.models.CommentDAO;
import web_project.repositories.models.PostDAO;
import web_project.repositories.models.UserDAO;

public record ReportInput(String content, UserDAO user, PostDAO post, CommentDAO comment) {
}
