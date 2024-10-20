package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Team;
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
public class JdbcTeamDao implements TeamDao{

    private final JdbcTemplate template;

    private RowMapper<Team> mapper = new RowMapper<Team>() {


        @Override
        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("team_name");
            Integer countryId = rs.getInt("country_id");
            Integer rankFromPreviousSeason = rs.getInt("rank_from_previous_season");
            Integer numberOfPlayersOnTeam = rs.getInt("number_of_players_on_team");
            Integer numberOfLeagueTitlesWon = rs.getInt("number_of_league_titles_won");
            Team team = new Team( name, countryId, rankFromPreviousSeason, numberOfPlayersOnTeam, numberOfLeagueTitlesWon);
            return team;
        }
    };

    public JdbcTeamDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Team> getAllTeams() {
        return template.query("SELECT * FROM team", mapper);
    }

    @Override
    public Team getTeamByName(String name) {
        String sql = "SELECT * FROM team WHERE team_name = ?";
        return template.queryForObject(sql, mapper, name);
    }


    @Override
    public Team updateTeam(String name, Team team) {
        Team updatedTeam = null;

        String sql = "UPDATE team SET country_id = ?, rank_from_previous_season = ?, number_of_players_on_team = ?, " +
                "number_of_league_titles_won = ? WHERE team_name = ?";

        try {
            int rowsAffected = template.update(sql, team.getCountryId(), team.getRankFromPreviousSeason(),
                    team.getNumberOfPlayersOnTeam(), team.getNumberOfLeagueTitlesWon(),
                    name);
        if(rowsAffected == 0){
            throw new DaoException("Zero rows affected, expected at least 1");
        }
            updatedTeam = getTeamByName(name);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }

        return updatedTeam;
        }

    @Override
    public void deleteTeam(String name) {
        template.update("DELETE FROM team WHERE team_name = ?", name);

    }

}
