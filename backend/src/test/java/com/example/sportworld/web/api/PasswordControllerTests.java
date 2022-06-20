package com.example.sportworld.web.api;

import com.example.sportworld.core.UserService;
import com.example.sportworld.repositories.UserRepository;
import com.example.sportworld.repositories.models.UserDAO;
import com.example.sportworld.web.api.models.ChangePasswordInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PasswordControllerTests {
    private UserRepository userRepository;
    private UserService userService;
    private PasswordController passwordController;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
        passwordController = new PasswordController(userService);
    }

    @Test
    public void changePasswordTest() {
        UserDAO user = new UserDAO(1, "Preslav", "34c0918b8e6e9f12ca2a8ecfd213011b29cfe9d2601bb870c20f51fc2a3ea946", "user@user.com", null, null, "VeqzfePhXlKNOLZ+ENromHTJLvs=", 1);
        ChangePasswordInput changePasswordInput = new ChangePasswordInput("user", "123456", "psdNew");
        when(userRepository.getUserByUsername(anyString())).thenReturn(user);
        when(userRepository.getUserByID(anyInt())).thenReturn(user);
        passwordController.changePassword(changePasswordInput);

        verify(userRepository, times(1)).changePassword(anyString(), anyString(), anyString());

    }
}
