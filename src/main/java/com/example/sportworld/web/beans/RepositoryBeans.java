package com.example.sportworld.web.beans;

import com.example.sportworld.repositories.UserRepository;
import com.example.sportworld.repositories.mysql.MySQLUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;


@Configuration
public class RepositoryBeans {

    @Bean
    public UserRepository studentRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLUserRepository(txTemplate, jdbcTemplate);
    }
}
