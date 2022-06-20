package com.example.sportworld.repositories.mysql;

import com.example.sportworld.repositories.MailTokenRepository;
import com.example.sportworld.repositories.exceptions.MailTokenNotFoundException;
import com.example.sportworld.repositories.models.MailTokenDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.sportworld.repositories.mysql.MySQLMailTokenRepository.Queries.*;

public class MySQLMailTokenRepository implements MailTokenRepository {
    private final TransactionTemplate txTemplate;
    private final JdbcTemplate jdbc;

    public MySQLMailTokenRepository(TransactionTemplate txTemplate, JdbcTemplate jdbc) {
        this.txTemplate = txTemplate;
        this.jdbc = jdbc;
    }

    @Override
    public MailTokenDAO createToken(String token, int userID) {
        return txTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
                        INSERT_TOKEN, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, token);
                ps.setInt(2, userID);

                return ps;
            }, keyHolder);

            //int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return getTokenByUserID(userID);
        });
    }

    @Override
    public MailTokenDAO getTokenByUserID(int userID) {
        try {
            return jdbc.queryForObject(GET_TOKEN_BY_USER_ID, (rs, rowNum) -> fromResultSet(rs), userID);
        } catch (MailTokenNotFoundException e) {
            throw new MailTokenNotFoundException();
        }
    }

    @Override
    public MailTokenDAO getToken(String token) {
        try {
            return jdbc.queryForObject(GET_TOKEN, (rs, rowNum) -> fromResultSet(rs), token);
        } catch (MailTokenNotFoundException e) {
            throw new MailTokenNotFoundException();
        }
    }

    @Override
    public void deleteToken(int userID) {
        try {
            txTemplate.execute(status -> {
                jdbc.update(DELETE_TOKEN, userID);
                return null;
            });
        } catch (MailTokenNotFoundException e) {
            throw new MailTokenNotFoundException();
        }
    }

    private MailTokenDAO fromResultSet(ResultSet rs) throws SQLException {
        return new MailTokenDAO(
                rs.getInt("id"),
                rs.getString("token"),
                rs.getInt("user_id")
        );
    }

    static class Queries {
        public static final String INSERT_TOKEN = "INSERT INTO mail_tokens (token, user_id) VALUES (?, ?)";

        public static final String GET_TOKEN_BY_USER_ID = "SELECT id, token, user_id FROM mail_tokens WHERE user_id = ?";

        public static final String GET_TOKEN = "SELECT id, token, user_id FROM mail_tokens WHERE token = ?";

        public static final String DELETE_TOKEN = "DELETE FROM mail_tokens WHERE user_id = ?";
    }
}
