package com.example.sportworld.core;

import com.example.sportworld.core.models.User;
import com.example.sportworld.repositories.models.UserDAO;

public class Mappers {
    public static User fromUserDAO(UserDAO user) {
        return new User(
                        user.id, user.username, user.passwordHash,
                        user.email, user.phoneNumber, user.registrationDate, user.salt, user.role_id);
    }

    /*public static Post fromPostDAO(PostDAO post) {
        return new Post(
                post.id(), post.title(), post.content(), post.votes(), post.views(),
                post.creationDate(), post.category(), post.tags(), fromUserDAO(post.user()));
    }

    public static Comment fromCommentDAO(CommentDAO comment) {
        return new Comment(
                comment.id(), comment.content(), comment.votes(), comment.views(), comment.creationDate(),
                fromUserDAO(comment.user()), fromPostDAO(comment.post()));
    }

    public static Report fromReportsDAO (ReportDAO report) {
        return new Report(
                report.content(), fromUserDAO(report.user()),
                fromPostDAO(report.post()), fromCommentDAO(report.comment()));
    }*/
}
