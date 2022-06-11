package com.example.sportworld.web.api;

import com.example.sportworld.core.UserService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sportworld.core.models.User;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/users")
public class UsersController {
    private UserService userService;
    Gson gson = new Gson();

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Integer id, @RequestHeader ("Authorization") String token) {

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        User gUser = gson.fromJson(payload, User.class);

        if (gUser.id != id) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            try {
                User user = userService.getUserByID(id);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
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
