package com.example.sportworld.core;

import com.example.sportworld.core.models.Comment;
import com.example.sportworld.repositories.CommentRepository;
import com.example.sportworld.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;

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
                .collect(Collectors.toList());
    }

    public List<Comment> listCommentsByMatch(int matchID) {
        List<Comment> list = commentRepository.listCommentsByMatch(matchID)
                .stream()
                .map(Mappers::fromCommentDAO)
                .collect(Collectors.toList());
        list.forEach(comment -> {
            comment.author = userRepository.getUserByID(comment.userID).username;
        });
        return list;
    }

    public Comment getComment(int id) {
        return Mappers.fromCommentDAO(commentRepository.getComment(id));
    }

    public void deleteComment(int id) {
        commentRepository.deleteComment(id);
    }
}
