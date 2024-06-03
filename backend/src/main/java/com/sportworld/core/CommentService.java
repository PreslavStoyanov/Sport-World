package com.sportworld.core;

import com.sportworld.core.models.Comment;
import com.sportworld.repositories.CommentRepository;
import com.sportworld.repositories.UserRepository;

import java.util.List;

public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(String content, int userID, int matchID) {
        return Mappers.fromCommentDAO(commentRepository.createComment(content, userID, matchID));
    }

    public List<Comment> listComments(int page, int pageSize) {
        return commentRepository.listComments(page, pageSize)
                .stream()
                .map(Mappers::fromCommentDAO)
                .toList();
    }

    public List<Comment> listCommentsByMatch(int matchID) {
        List<Comment> list = commentRepository.listCommentsByMatch(matchID)
                .stream()
                .map(Mappers::fromCommentDAO)
                .toList();
        list.forEach(comment -> comment.setAuthor(userRepository.getUserByID(comment.getUserID()).username()));
        return list;
    }

    public Comment getComment(int id) {
        return Mappers.fromCommentDAO(commentRepository.getComment(id));
    }

    public void deleteComment(int id) {
        commentRepository.deleteComment(id);
    }
}
