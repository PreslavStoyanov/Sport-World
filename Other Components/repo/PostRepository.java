package web_project.repositories;

import web_project.repositories.models.PostDAO;

import java.sql.Timestamp;
import java.util.List;

public interface PostRepository {
    PostDAO createPost(String title, String content, int views, int votes, Timestamp creationDate, int category_id, int user_id);

    PostDAO getPost(int id);

    List<PostDAO> listPosts(int page, int pageSize);

    void deletePost(int id);
}
