package com.sportworld.bin.beans;

import com.sportworld.core.*;
import com.sportworld.repositories.*;
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

    @Bean
    public CommentService commentService(CommentRepository repository, UserRepository userRepository) {
        return new CommentService(repository, userRepository);
    }

    @Bean MailTokenService mailTokenService(MailTokenRepository repository) {
        return new MailTokenService(repository);
    }
}
