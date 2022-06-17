package com.example.sportworld.repositories.mysql;

import com.example.sportworld.repositories.UserRepository;
import com.example.sportworld.repositories.exceptions.UserNotFoundException;
import com.example.sportworld.repositories.models.UserDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import static com.example.sportworld.repositories.mysql.SQLUserRepository.Queries.*;

public class SQLUserRepository implements UserRepository {
    private final TransactionTemplate txTemplate;
    private final JdbcTemplate jdbc;

    public SQLUserRepository(TransactionTemplate txTemplate, JdbcTemplate jdbc) {
        this.txTemplate = txTemplate;
        this.jdbc = jdbc;
    }

    @Override
    public UserDAO createUser(String username, String passwordHash, String email, String salt) {
        return txTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
                        INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, passwordHash);
                ps.setString(3, email);
                ps.setString(4, salt);

                return ps;
            }, keyHolder);

            int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return new UserDAO(id, username, passwordHash, email, null, null, salt, null);
        });
    }

    @Override
    public void changePassword(String username, String passwordHash, String salt) {
        try {
            txTemplate.execute(status -> {
                jdbc.update(UPDATE_PASSWORD_AND_SALT, passwordHash, salt, username);
                return null;
            });
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public UserDAO getUserByID(int id) {
        try {
            return jdbc.queryForObject(GET_USER_BY_ID, (rs, rowNum) -> fromResultSet(rs), id);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public UserDAO getUserByUsername(String username) {
        try {
            return jdbc.queryForObject(GET_USER_BY_USERNAME, (rs, rowNum) -> fromResultSet(rs), username);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<UserDAO> listUsers(int page, int pageSize) {
        return jdbc.query(LIST_USERS, (rs, rowNum) -> fromResultSet(rs), page * pageSize, pageSize);
    }

    @Override
    public void deleteUser(int id) {
        try {
            txTemplate.execute(status -> {
                jdbc.update(DELETE_USER, id);
                return null;
            });
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
    }

    private UserDAO fromResultSet(ResultSet rs) throws SQLException {
        return new UserDAO(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getTimestamp("registration_date"),
                rs.getString("salt"),
                rs.getInt("role_id")
        );
    }

    static class Queries {
        public static final String UPDATE_PASSWORD_AND_SALT = "UPDATE users SET password_hash = ?, salt = ? WHERE username = ?";

        public static final String INSERT_USER = "INSERT INTO users (username, password_Hash, email, salt) VALUES (?, ?, ?, ?)";

        public static final String LIST_USERS = "SELECT * FROM users LIMIT ?, ?";

        public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";

        public static final String GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

        public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    }
}