package web_project.repositories.models;

public record ReportDAO(String content, UserDAO user, PostDAO post, CommentDAO comment) {
}
