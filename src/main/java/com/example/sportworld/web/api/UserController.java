package com.example.sportworld.web.api;

import com.example.sportworld.core.UserService;
import com.google.gson.Gson;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sportworld.core.models.User;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    Gson gson = new Gson();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id/*, @RequestHeader ("Authorization") String token*/) {

        /*String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        System.out.println(payload);
        User gUser = gson.fromJson(payload, User.class);
        System.out.println(gUser.id);
        System.out.println(gUser.username);
        System.out.println(gUser.email);
        System.out.println(gUser.passwordHash);
        System.out.println(gUser.phoneNumber);
        System.out.println(gUser.registrationDate);
        System.out.println(gUser.salt);
        System.out.println(gUser.role_id);
        System.out.println(id);
        if (gUser.id != id) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {

        }*/
        try {
            User user = userService.getUserByID(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public List<User> listUsers(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return userService.listUsers(page, pageSize);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
