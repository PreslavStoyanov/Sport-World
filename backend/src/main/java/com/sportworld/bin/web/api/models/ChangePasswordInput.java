package com.sportworld.bin.web.api.models;

public class ChangePasswordInput {
    public final String username;
    public final String oldPassword;
    public final String newPassword;

    public ChangePasswordInput(String username, String oldPassword, String newPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
