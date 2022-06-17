package com.example.sportworld.repositories;

import com.example.sportworld.repositories.models.CommentDAO;

import java.util.List;

public interface CommentRepository {
    CommentDAO createComment(String content, int userID, int matchID);

    CommentDAO getComment(int id);

    List<CommentDAO> listComments(int page, int pageSize);

    List<CommentDAO> listCommentsByMatch(int matchID);

    void deleteComment(int id);
}
