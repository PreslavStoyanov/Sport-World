package com.example.sportworld.web.api;

import com.example.sportworld.core.UserService;
import com.example.sportworld.core.exceptions.InvalidUserParameterException;
import com.example.sportworld.web.api.models.ChangePasswordInput;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/users")
public class PasswordController {

    private final UserService userService;

    public PasswordController(UserService userService) {
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
}
