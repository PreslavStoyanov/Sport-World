package com.example.sportworld.core;

import com.example.sportworld.core.models.*;
import com.example.sportworld.repositories.models.*;

public class Mappers {
    public static User fromUserDAO(UserDAO user) {
        return new User(
                user.id, user.username, user.passwordHash,
                user.email, user.phoneNumber, user.registrationDate, user.salt, user.role_id);
    }

    public static League fromLeagueDAO(LeagueDAO league) {
        return new League(
                league.id, league.name);
    }

    public static Match fromMatchDAO(MatchDAO match) {
        return new Match(
                match.id, match.title, match.content,
                match.creationDate, fromLeagueDAO(match.league), match.userID);
    }

    public static Comment fromCommentDAO(CommentDAO comment) {
        return new Comment(
                comment.id, comment.content, comment.creationDate,
                comment.userID, comment.matchID);
    }

    public static MailToken fromMailTokenDAO(MailTokenDAO mailToken) {
        return new MailToken(mailToken.id, mailToken.token, mailToken.userID);
    }
}
