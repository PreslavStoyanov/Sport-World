package com.sportworld.repositories.mysql;

import com.sportworld.repositories.CommentRepository;
import com.sportworld.repositories.exceptions.CommentNotFoundException;
import com.sportworld.repositories.models.CommentDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import static com.sportworld.repositories.mysql.MySQLCommentRepository.Queries.*;

public class MySQLCommentRepository implements CommentRepository {
    private final TransactionTemplate txTemplate;
    private final JdbcTemplate jdbc;

    public MySQLCommentRepository(TransactionTemplate txTemplate, JdbcTemplate jdbc) {
        this.txTemplate = txTemplate;
        this.jdbc = jdbc;
    }

    @Override
    public CommentDAO createComment(String content, int userID, int matchID) {
        return txTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
                        INSERT_COMMENT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, content);
                ps.setInt(2, userID);
                ps.setInt(3, matchID);

                return ps;
            }, keyHolder);

            int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return getComment(id);
        });
    }

    @Override
    public CommentDAO getComment(int id) {
        try {
            return jdbc.queryForObject(GET_COMMENT, (rs, rowNum) -> fromResultSet(rs), id);
        } catch (CommentNotFoundException e) {
            throw new CommentNotFoundException();
        }
    }

    @Override
    public List<CommentDAO> listComments(int page, int pageSize) {
        return jdbc.query(LIST_COMMENTS, (rs, rowNum) -> fromResultSet(rs), page * pageSize, pageSize);
    }

    public List<CommentDAO> listCommentsByMatch(int matchID) {
        return jdbc.query(LIST_COMMENTS_BY_MATCH, (rs, rowNum) -> fromResultSet(rs), matchID);
    }

    @Override
    public void deleteComment(int id) {
        try {
            txTemplate.execute(status -> {
                jdbc.update(DELETE_COMMENT, id);
                return null;
            });
        } catch (CommentNotFoundException e) {
            throw new CommentNotFoundException();
        }
    }

    private CommentDAO fromResultSet(ResultSet rs) throws SQLException {
        return new CommentDAO(
                rs.getInt("id"),
                rs.getString("content"),
                rs.getTimestamp("creation_date"),
                rs.getInt("user_id"),
                rs.getInt("match_id")
        );
    }

    static class Queries {

        public static final String INSERT_COMMENT = "INSERT INTO comments(content, user_id, match_id) VALUES (?, ?, ?)";

        public static final String GET_COMMENT = """
                SELECT\s
                    id,
                    content,
                    creation_date,\s
                    user_id,
                    match_id\s
                FROM
                    comments\s
                WHERE
                    id = ?""";

        public static final String LIST_COMMENTS = """
                SELECT\s
                    id,
                    content,
                    creation_date,\s
                    user_id,
                    match_id\s
                FROM
                    comments
                LIMIT ?, ?""";

        public static final String LIST_COMMENTS_BY_MATCH = """
                SELECT\s
                    id,
                    content,
                    creation_date,\s
                    user_id,
                    match_id\s
                FROM
                    comments
                WHERE
                    match_id = ?
                ORDER BY creation_date DESC""";

        public static final String DELETE_COMMENT = "DELETE FROM comments WHERE id = ?";
    }

}
