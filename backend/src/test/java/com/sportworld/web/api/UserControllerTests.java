package com.sportworld.web.api;

import com.sportworld.bin.web.api.UserController;
import com.sportworld.core.UserService;
import com.sportworld.core.models.User;
import com.sportworld.repositories.UserRepository;
import com.sportworld.repositories.models.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class UserControllerTests {
    private UserController userController;
    private UserService userService;
    private UserRepository userRepository;
    private static final String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiUHJlc2xhdiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyIiwicm9sZUlkIjoxLCJpYXQiOjE2NTU0NzIyOTEsImV4cCI6MTY1NTUzMjI5MX0.7mIC5PZG0OKSTeK0UruIcPPPyPSnVDCkX06HVxkAwBZQ4dyYR_jSDpFnt5rBdtnpOxx8Nxd_bYI3SKmjRKP9zw";
    private final UserDAO template = new UserDAO(1, "user",
            "psd", "user@abv.com", null, null, "123", 1);


    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
        userController = new UserController(userService);
    }

    @Test
    void getUserTest() {
        User tokenInfo = userService.getUserInfoByToken(token);
        when(userRepository.getUserByID(anyInt())).thenReturn(template);

        assertEquals(template.id(), Objects.requireNonNull(tokenInfo).getId());
        assertEquals(template.username(), tokenInfo.getUsername());
        assertEquals(template.roleId(), tokenInfo.getRoleId());

        ResponseEntity<?> response = userController.getUser(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listUsersTest() {
        List<UserDAO> users = new ArrayList<>();
        users.add(template);
        when(userRepository.listUsers(anyInt(), anyInt())).thenReturn(users);


        List<User> response = userController.listUsers(0, 1);


        assertEquals(users.size(), response.size());
        for (int i = 0; i < users.size(); i++) {
            assertEquals(users.get(i).id(), response.get(i).getId());
            assertEquals((users.get(i).username()), response.get(i).getUsername());
        }
    }

    @Test
    void deleteUserTest() {
        userController.deleteUser(1);

        verify(userRepository, times(1)).deleteUser(anyInt());

    }
}
