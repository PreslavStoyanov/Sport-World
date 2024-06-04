package com.sportworld.bin.web.api.models;

public record ChangePasswordInput(String username, String oldPassword, String newPassword) {
}
