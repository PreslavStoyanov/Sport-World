package com.sportworld.repositories;

import com.sportworld.repositories.models.UserDAO;

import java.util.List;

public interface UserRepository {
    UserDAO createUser(String username, String passwordHash, String email, String salt);

    void changePassword(String username, String passwordHash, String salt);

    UserDAO getUserByID(int id);

    UserDAO getUserByUsername(String username);

    List<UserDAO> listUsers(int page, int pageSize);

    void deleteUser(int id);
}
