package com.example.sportworld.web.api;

import com.example.sportworld.core.UserService;
import com.example.sportworld.core.models.User;
import com.example.sportworld.repositories.UserRepository;
import com.example.sportworld.repositories.models.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserControllerTests {
    private UserController userController;
    private UserService userService;
    private UserRepository userRepository;
    private static final String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViI" +
            "joiUHJlc2xhdiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6MSwidXNlcm5hbWUiOiJ1c2V" +
            "yIiwicm9sZV9pZCI6MSwiaWF0IjoxNjU1NDcyMjkxLCJleHAiOjE2NTU1MzIyOTF9.PGuBzVg43t6sFFXv" +
            "fU4_blDxVlZOsN2XIog-E5G5vM125lH-kSIse3bjB5bJxWI-7cX_vQpSaUoEAzcUzFhXrw";
    private final UserDAO template = new UserDAO(1, "user",
            "psd", "user@abv.com", null, null, "123", 1);


    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
        userController = new UserController(userService);
    }

    @Test
    public void getUserTest() {
        User tokenInfo = userService.getUserInfoByToken(token);
        when(userRepository.getUserByID(anyInt())).thenReturn(template);

        assertEquals(template.id, Objects.requireNonNull(tokenInfo).id);
        assertEquals(template.username, tokenInfo.username);
        assertEquals(template.role_id, tokenInfo.role_id);

        ResponseEntity<?> response = userController.getUser(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void listUsersTest() {
        List<UserDAO> users = new ArrayList<>();
        users.add(template);
        when(userRepository.listUsers(anyInt(), anyInt())).thenReturn(users);


        List<User> response = userController.listUsers(0, 1);


        assertEquals(users.size(), response.size());
        for (int i = 0; i < users.size(); i++) {
            assertEquals(users.get(i).id, response.get(i).id);
            assertEquals((users.get(i).username), response.get(i).username);
        }
    }

    @Test
    public void deleteUserTest() {
        userController.deleteUser(1);

        verify(userRepository, times(1)).deleteUser(anyInt());

    }
}
