package com.example.sportworld.repositories.mysql;

import com.example.sportworld.repositories.LeagueRepository;
import com.example.sportworld.repositories.exceptions.LeagueNotFoundException;
import com.example.sportworld.repositories.models.LeagueDAO;
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

import static com.example.sportworld.repositories.mysql.MySQLLeagueRepository.Queries.*;

public class MySQLLeagueRepository implements LeagueRepository {
    private final TransactionTemplate txTemplate;
    private final JdbcTemplate jdbc;

    public MySQLLeagueRepository(TransactionTemplate txTemplate, JdbcTemplate jdbc) {
        this.txTemplate = txTemplate;
        this.jdbc = jdbc;
    }

    @Override
    public LeagueDAO createLeague(String name) {
        return txTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
                        INSERT_LEAGUE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);

                return ps;
            }, keyHolder);

            int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return getLeague(id);
        });
    }

    @Override
    public LeagueDAO getLeague(int id) {
        try {
            return jdbc.queryForObject(GET_LEAGUE_BY_ID, (rs, rowNum) -> fromResultSet(rs), id);
        } catch (LeagueNotFoundException e) {
            throw new LeagueNotFoundException();
        }
    }

    @Override
    public List<LeagueDAO> listLeagues(int page, int pageSize) {
        return jdbc.query(LIST_LEAGUES, (rs, rowNum) -> fromResultSet(rs), page * pageSize, pageSize);
    }

    @Override
    public void deleteLeague(int id) {
        try {
            txTemplate.execute(status -> {
                jdbc.update(DELETE_LEAGUE, id);
                return null;
            });
        } catch (LeagueNotFoundException e) {
            throw new LeagueNotFoundException();
        }
    }

    private LeagueDAO fromResultSet(ResultSet rs) throws SQLException {
        return new LeagueDAO(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    static class Queries {
        public static final String INSERT_LEAGUE = "INSERT INTO leagues (name) VALUES (?)";

        public static final String LIST_LEAGUES = "SELECT * FROM leagues LIMIT ?, ?";

        public static final String GET_LEAGUE_BY_ID = "SELECT * FROM leagues WHERE id = ?";

        public static final String GET_LEAGUE_BY_NAME = "SELECT * FROM leagues WHERE name = ?";

        public static final String DELETE_LEAGUE = "DELETE FROM leagues WHERE id = ?";
    }
}
