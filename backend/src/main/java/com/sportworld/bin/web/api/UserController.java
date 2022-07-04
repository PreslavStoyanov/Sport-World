package com.sportworld.bin.web.api;

import com.sportworld.core.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sportworld.core.models.User;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/current")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String token) {
        User jsonUser = userService.getUserInfoByToken(token);
        try {
            User user = userService.getUserByID(jsonUser.id);
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
