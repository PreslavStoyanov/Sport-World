package web_project.repositories;

import web_project.repositories.models.CommentDAO;

import java.sql.Timestamp;
import java.util.List;

public interface CommentRepository {
    CommentDAO createComment(String content, int votes, int views, Timestamp creationDate, int user_id, int post_id );

    CommentDAO getComment(int id);

    List<CommentDAO> listComments(int page, int pageSize);

    void deleteComment(int id);
}
