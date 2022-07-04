package com.sportworld.repositories.mysql;

import com.sportworld.repositories.MatchRepository;
import com.sportworld.repositories.exceptions.MatchNotFoundException;
import com.sportworld.repositories.models.LeagueDAO;
import com.sportworld.repositories.models.MatchDAO;
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

import static com.sportworld.repositories.mysql.MySQLMatchRepository.Queries.*;

public class MySQLMatchRepository implements MatchRepository {
    private final TransactionTemplate txTemplate;
    private final JdbcTemplate jdbc;

    public MySQLMatchRepository(TransactionTemplate txTemplate, JdbcTemplate jdbc) {
        this.txTemplate = txTemplate;
        this.jdbc = jdbc;
    }

    @Override
    public MatchDAO createMatch(String title, String content, int leagueID, int userID) {
        return txTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
                        INSERT_MATCH, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setString(2, content);
                ps.setInt(3, leagueID);
                ps.setInt(4, userID);

                return ps;
            }, keyHolder);

            int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return getMatch(id);
        });
    }

    @Override
    public MatchDAO getMatch(int id) {
        try {
            return jdbc.queryForObject(GET_MATCH, (rs, rowNum) -> fromResultSet(rs), id);
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException();
        }
    }

    @Override
    public List<MatchDAO> listMatches(int page, int pageSize) {
        return jdbc.query(LIST_MATCHES, (rs, rowNum) -> fromResultSet(rs), page * pageSize, pageSize);
    }

    @Override
    public void deleteMatch(int id) {
        try {
            txTemplate.execute(status -> {
                jdbc.update(DELETE_MATCH, id);
                return null;
            });
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException();
        }
    }

    private MatchDAO fromResultSet(ResultSet rs) throws SQLException {
        return new MatchDAO(
                rs.getInt("match_id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("creation_date"),
                new LeagueDAO(
                        rs.getInt("league_id"),
                        rs.getString("league")),
                rs.getInt("user")
        );
    }

    static class Queries {
        public static final String INSERT_MATCH = "INSERT INTO matches (title, content, league_id, user_id) VALUES (?, ?, ?, ?)";

        public static final String GET_MATCH = """
                SELECT\s
                    m.id AS match_id,
                    m.title AS title,
                    m.content AS content,
                    m.creation_date AS creation_date,
                    l.id AS league_id,
                    l.name AS league,
                    m.user_id AS user
                FROM
                    matches m
                        JOIN
                    leagues l ON m.league_id = l.id
                WHERE
                    m.id = ?""";

        public static final String LIST_MATCHES = """
                SELECT\s
                    m.id as match_id,
                    m.title as title,
                    m.content as content,
                    m.creation_date as creation_date,
                    l.id as league_id,
                    l.name as league,
                    m.user_id AS user
                FROM
                    matches m
                JOIN
                    leagues l ON m.league_id = l.id
                LIMIT ?, ?""";

        public static final String DELETE_MATCH = "DELETE FROM matches WHERE id = ?";
    }
}
