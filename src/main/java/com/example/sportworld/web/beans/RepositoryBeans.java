package com.example.sportworld.web.beans;

import com.example.sportworld.repositories.*;
import com.example.sportworld.repositories.mysql.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;


@Configuration
public class RepositoryBeans {

    @Bean
    public UserRepository userRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new SQLUserRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    LeagueRepository leagueRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new SQLLeagueRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    MatchRepository matchRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new SQLMatchRepository(txTemplate, jdbcTemplate);
    }
}
