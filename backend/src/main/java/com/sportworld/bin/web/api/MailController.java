package com.sportworld.bin.web.api;

import com.sportworld.gateways.EmailsGateway;
import com.sportworld.core.MailTokenService;
import com.sportworld.core.UserService;
import com.sportworld.core.models.MailToken;
import com.sportworld.core.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class MailController {
    private final EmailsGateway emailsGateway;
    private final UserService userService;
    private final MailTokenService mailTokenService;

    public MailController(EmailsGateway emailsGateway, UserService userService, MailTokenService mailTokenService) {
        this.emailsGateway = emailsGateway;
        this.userService = userService;
        this.mailTokenService = mailTokenService;
    }

    @PostMapping(value = "/forgotPassword")
    public void getForgotPasswordUsername(@RequestParam String username) {
        User user = userService.getUserByUsername(username);
        Random num = new Random();

        String token = String.valueOf(num.nextInt((20000 - 10000) + 1) + 10000);
        mailTokenService.createToken(token, user.id);
        emailsGateway.sendMail("Sport-world: Password reset", String.format("""
                Please get this code:\s
                %s
                Paste it in site form to verify.""", token), user.email);

    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity<?> verifyCode(@RequestParam String token, @RequestParam String newPassword) {
        try {
            MailToken mailToken = mailTokenService.getToken(token);
            User user = userService.getUserByID(mailToken.userID);
            userService.changePassword(user.username, newPassword);
            mailTokenService.deleteToken(mailToken.userID);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
