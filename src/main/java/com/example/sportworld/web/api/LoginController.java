package com.example.sportworld.web.api;

import com.example.sportworld.core.exceptions.InvalidUserParameterException;
import com.example.sportworld.core.models.User;
import com.example.sportworld.web.api.models.UserInput;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.sportworld.core.UserService;
import com.example.sportworld.web.api.models.LoginInput;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public User createUser(@RequestBody UserInput user) {
        return userService.createUser(
                user.username, user.password, user.email);
    }

    @PostMapping("/login")
    public LoginInput login(@RequestBody UserInput user) {
        int userID = userService.getUserByUsername(user.username).id;
        try {
            userService.authorizeUser(userID, user.password);
        } catch (InvalidUserParameterException e) {
            throw new IllegalArgumentException();
        }
        String token = getJWTToken(user.username);
        return new LoginInput(String.valueOf(userID), token);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
