package com.example.sportworld.web.beans;

import com.example.sportworld.core.UserService;
import com.example.sportworld.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreBeans {
    @Bean
    public UserService userService(UserRepository repository) {
        return new UserService(repository);
    }
}
