package com.sportworld.core;

import com.sportworld.core.exceptions.InvalidUserParameterException;
import com.sportworld.repositories.UserRepository;
import com.sportworld.core.models.User;
import com.sportworld.repositories.exceptions.UserNotFoundException;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;
    Gson gson = new Gson();
    private final String paper = "fc3a3c20-a28d-4454-90dd-bf054315df22";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authorizeUser(int userID, String password) {
        User user;
        try {
            user = Mappers.fromUserDAO(userRepository.getUserByID(userID));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        String hash = sha256(user.salt + password + paper);
        if (!hash.equals(user.passwordHash))
            throw new InvalidUserParameterException();
    }

    public User createUser(String username, String password, String email) {
        String salt = generateSalt();

        String passwordHash = sha256(salt + password + paper);

        return Mappers.fromUserDAO(userRepository.createUser(username, passwordHash, email, salt));
    }

    public User getUserInfoByToken(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        return gson.fromJson(payload, User.class);
    }

    public void changePassword (String username, String newPassword) {
        String salt = generateSalt();
        String passwordHash = sha256(salt + newPassword + paper);

        userRepository.changePassword(username, passwordHash, salt);
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
