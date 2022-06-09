package com.example.sportworld.core;

import com.example.sportworld.core.exceptions.InvalidUserParameterException;
import com.example.sportworld.repositories.UserRepository;
import com.example.sportworld.core.models.User;
import com.example.sportworld.repositories.exceptions.UserNotFoundException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authorizeUser(String username, String password) {
        User user;
        try {
            user = Mappers.fromUserDAO(userRepository.getUserByUsername(username));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        String hash = sha256(user.salt + password);
        if (!hash.equals(user.passwordHash))
            throw new InvalidUserParameterException();
    }

    public User createUser(String username, String password, String email, String phoneNumber) {
        String salt = generateSalt();

        String passwordHash = sha256(salt + password);

        return Mappers.fromUserDAO(userRepository.createUser(username, passwordHash, email, phoneNumber, salt));
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static String sha256(String originalString) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public List<User> listUsers(int page, int pageSize) {
        return userRepository.listUsers(page, pageSize)
                .stream()
                .map(Mappers::fromUserDAO)
                .collect(Collectors.toList());
    }

    public User getUserByID(int id) {
        return Mappers.fromUserDAO(userRepository.getUserByID(id));
    }

    public User getUserByUsername(String username) {
        return Mappers.fromUserDAO(userRepository.getUserByUsername(username));
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }
}
