package com.example.sportworld.web.beans;

import com.example.sportworld.core.*;
import com.example.sportworld.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreBeans {
    @Bean
    public UserService userService(UserRepository repository) {
        return new UserService(repository);
    }

    @Bean
    public LeagueService leagueService(LeagueRepository repository) {
        return new LeagueService(repository);
    }

    @Bean
    public MatchService matchService(MatchRepository repository) {
        return new MatchService(repository);
    }
}
