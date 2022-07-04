package com.sportworld.bin.beans;

import com.sportworld.repositories.*;
import com.sportworld.repositories.mysql.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;


@Configuration
public class RepositoryBeans {

    @Bean
    public UserRepository userRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLUserRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    LeagueRepository leagueRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLLeagueRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    MatchRepository matchRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLMatchRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    CommentRepository commentRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLCommentRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    MailTokenRepository mailTokenRepository(TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLMailTokenRepository(txTemplate, jdbcTemplate);
    }
}
