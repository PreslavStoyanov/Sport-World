package com.example.sportworld.core;

import com.example.sportworld.core.models.MailToken;
import com.example.sportworld.repositories.MailTokenRepository;

public class MailTokenService {
    private final MailTokenRepository mailTokenRepository;

    public MailTokenService(MailTokenRepository mailTokenRepository) {
        this.mailTokenRepository = mailTokenRepository;
    }


    public MailToken createToken(String token, int userID) {
        return Mappers.fromMailTokenDAO(mailTokenRepository.createToken(token, userID));
    }


    public MailToken getTokenByUserID(int userID) {
        return Mappers.fromMailTokenDAO(mailTokenRepository.getTokenByUserID(userID));
    }

    public MailToken getToken(String token) {
        return Mappers.fromMailTokenDAO(mailTokenRepository.getToken(token));
    }

    public void deleteToken(int userID) {
        mailTokenRepository.deleteToken(userID);
    }
}
