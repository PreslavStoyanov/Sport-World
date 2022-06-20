package com.example.sportworld.repositories;

import com.example.sportworld.repositories.models.MailTokenDAO;

public interface MailTokenRepository {
    MailTokenDAO createToken(String token, int userID);

    MailTokenDAO getTokenByUserID(int userID);

    MailTokenDAO getToken(String token);

    void deleteToken(int userID);
}
