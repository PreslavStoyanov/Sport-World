package com.sportworld.bin.web.api;

import com.sportworld.bin.web.api.models.LoginInput;
import com.sportworld.bin.web.api.models.UserInput;
import com.sportworld.core.exceptions.InvalidUserParameterException;
import com.sportworld.core.models.User;
import com.sportworld.gateways.KafkaGateway;
import com.sportworld.lib.events.UserCreatedEvent;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sportworld.core.UserService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    private final UserService userService;
    private KafkaGateway kafkaGateway;

    public LoginController(UserService userService, KafkaGateway kafkaGateway) {
        this.userService = userService;
        this.kafkaGateway = kafkaGateway;
    }

    @PostMapping(value = "/register")
    public User createUser(@RequestBody UserInput user) {
        User u = userService.createUser(
                user.username, user.password, user.email);
        kafkaGateway.sendUserCreatedEvent(new UserCreatedEvent(u, LocalDateTime.now()));

        return u;
    }

    @PostMapping("/login")
    public LoginInput login(@RequestBody UserInput user) {
        int userID = userService.getUserByUsername(user.username).getId();
        int roleID = userService.getUserByUsername(user.username).getRoleId();
        try {
            userService.authorizeUser(userID, user.password);
        } catch (InvalidUserParameterException e) {
            throw new IllegalArgumentException();
        }
        String token = getJWTToken(user.username, userID, roleID);
        return new LoginInput(String.valueOf(userID), token);
    }

    private String getJWTToken(String username, int userID, int roleID) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).toList())
                .claim("id", userID)
                .claim("username", username)
                .claim("roleId", roleID)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
