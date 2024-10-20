package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.League;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcLeagueDao implements LeagueDao {

    private final JdbcTemplate template;


    private RowMapper<League> mapper = new RowMapper<League>() {
        @Override
        public League mapRow(ResultSet rs, int rowNum) throws SQLException {
            String leagueName = rs.getString("league_name");
            int countryId = rs.getInt("country_id");
            int numberOfTeamsInLeague = rs.getInt("number_of_teams_in_league");
            int leagueDivision = rs.getInt("league_division");
            League league = new League( leagueName, countryId, numberOfTeamsInLeague, leagueDivision);
            return league;
        }
    };

    public JdbcLeagueDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<League> getAllLeagues() {
        return template.query("SELECT * FROM league", mapper);
    }

    @Override
    public League getLeagueByName(String name) {
        String sql = "SELECT league_id, league_name, country_id, number_of_teams_in_league, " +
                "league_division WHERE league_id = ?";
        return template.queryForObject(sql, mapper, name);
    }

}



