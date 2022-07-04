package com.sportworld.core.models;

import java.sql.Timestamp;

public class User {
    public int id;
    public String username;
    public String passwordHash;
    public String email;
    public String phoneNumber;
    public Timestamp registrationDate;
    public String salt;
    public Integer role_id;

    public User() {
    }

    public User(int id, String username, String passwordHash, String email, String phoneNumber, Timestamp registrationDate, String salt, Integer role_id) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.salt = salt;
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", salt='" + salt + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}
