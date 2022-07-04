package com.sportworld.repositories.models;

import java.sql.Timestamp;

public class UserDAO {
    public final int id;
    public final String username;
    public final String passwordHash;
    public final String email;
    public final String phoneNumber;
    public final Timestamp registrationDate;
    public final String salt;
    public final Integer role_id;

    public UserDAO(int id, String username, String passwordHash, String email, String phoneNumber, Timestamp registrationDate, String salt, Integer role_id) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.salt = salt;
        this.role_id = role_id;
    }

}
