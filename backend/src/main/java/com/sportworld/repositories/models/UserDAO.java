package com.sportworld.repositories.models;

import java.sql.Timestamp;

public record UserDAO(int id, String username, String passwordHash, String email, String phoneNumber,
                      Timestamp registrationDate, String salt, Integer roleId) {
}
