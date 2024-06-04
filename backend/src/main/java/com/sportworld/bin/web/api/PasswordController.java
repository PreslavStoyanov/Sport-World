package com.sportworld.bin.web.api;

import com.sportworld.core.UserService;
import com.sportworld.core.exceptions.InvalidUserParameterException;
import com.sportworld.bin.web.api.models.ChangePasswordInput;
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
            userService.authorizeUser(userService.getUserByUsername(input.username()).getId(), input.oldPassword());
        } catch (InvalidUserParameterException e) {
            throw new InvalidUserParameterException();
        }
        userService.changePassword(input.username(), input.newPassword());
    }
}
