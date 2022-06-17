package com.example.sportworld.web.api;

import com.example.sportworld.core.UserService;
import com.example.sportworld.core.exceptions.InvalidUserParameterException;
import com.example.sportworld.web.api.models.ChangePasswordInput;
import com.google.gson.Gson;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sportworld.core.models.User;

import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/changePassword")
    public void changePassword(@RequestBody ChangePasswordInput input) {
        try {
            userService.authorizeUser(userService.getUserByUsername(input.username).id, input.oldPassword);
        } catch (InvalidUserParameterException e) {
            throw new InvalidUserParameterException();
        }
        userService.changePassword(input.username, input.newPassword);
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
