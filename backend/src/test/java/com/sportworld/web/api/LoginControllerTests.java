package com.sportworld.web.api;

import com.sportworld.bin.web.api.LoginController;
import com.sportworld.core.UserService;
import com.sportworld.core.models.User;
import com.sportworld.gateways.KafkaGateway;
import com.sportworld.lib.events.UserCreatedEvent;
import com.sportworld.lib.events.UserNotificationEmailJob;
import com.sportworld.repositories.UserRepository;
import com.sportworld.repositories.models.UserDAO;
import com.sportworld.bin.web.api.models.LoginInput;
import com.sportworld.bin.web.api.models.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LoginControllerTests {
    @Value("${com.sportworld.createuser}")
    private String userCreatedTopic;

    @Value("${com.sportworld.user-notification-email}")
    private String userNotificationEmailJobTopic;

    @Value("${4}")
    private Integer userNotificationEmailJobPartitionsCnt;

    private LoginController loginController;
    private UserRepository userRepository;
    private static final String mockToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiUHJlc2xhdiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6MSwidXNlcm5hbWUiOiJQcmVzbGF2Iiwicm9sZV9pZCI6MSwiaWF0IjoxNjU1NjI0MTE3LCJleHAiOjE2NTU2ODQxMTd9.IIxndom5Dd5vlm9Qi-ZJ3YPFqdgt8zs0iBve47uf-UzWPopzD4irRNl1xzhXFNgYNjX5N3ENZfutpVNmTNlIfw";

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        KafkaTemplate<String, UserCreatedEvent> userCreatedPublisher = Mockito.mock(KafkaTemplate.class);
        KafkaTemplate<String, UserNotificationEmailJob> userNotificationEmailJobPublisher = Mockito.mock(KafkaTemplate.class);
        loginController = new LoginController(
                new UserService(userRepository),
                new KafkaGateway(
                        userCreatedTopic,
                        userNotificationEmailJobTopic,
                        userNotificationEmailJobPartitionsCnt,
                        userCreatedPublisher, userNotificationEmailJobPublisher));
    }

    @Test
    void createUserTest() {
        UserDAO userDAO = new UserDAO(1, "user", "123", "user@user.com", null, null, "123", 1);
        when(userRepository.createUser(anyString(), anyString(), anyString(), anyString())).thenReturn(userDAO);
        UserInput userInput = new UserInput("user", "123", "user@user.com");

        User response = loginController.createUser(userInput);

        assertEquals(1, response.getId());
        assertEquals("user", response.getUsername());
        assertEquals("123", response.getPasswordHash());
        assertEquals("user@user.com", response.getEmail());
        assertEquals("123", response.getSalt());
        assertEquals(1, response.getRoleId());
    }

    @Test
    void loginTest() {
        UserDAO userDAO = new UserDAO(1, "Preslav", "34c0918b8e6e9f12ca2a8ecfd213011b29cfe9d2601bb870c20f51fc2a3ea946", "user@user.com", null, null, "VeqzfePhXlKNOLZ+ENromHTJLvs=", 1);

        when(userRepository.getUserByUsername(anyString())).thenReturn(userDAO);
        when(userRepository.getUserByID(anyInt())).thenReturn(userDAO);
        UserInput userInput = new UserInput("Preslav", "123456", "user@user.com");


        LoginInput response = loginController.login(userInput);

        assertEquals("1", response.userID());
        assertNotEquals(mockToken, response.token());
    }
}
