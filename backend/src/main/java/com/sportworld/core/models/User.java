package com.sportworld.core.models;

import java.sql.Timestamp;

public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String email;
    private String phoneNumber;
    private Timestamp registrationDate;
    private String salt;
    private Integer roleId;

    public User() {
    }

    public User(int id, String username, String passwordHash, String email, String phoneNumber, Timestamp registrationDate, String salt, Integer roleId) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.salt = salt;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public String getSalt() {
        return salt;
    }

    public Integer getRoleId() {
        return roleId;
    }
}
