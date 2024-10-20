package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Agent;
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
public class JdbcAgentDao implements AgentDao{

private final JdbcTemplate template;

private RowMapper<Agent> mapper = new RowMapper<Agent>() {
    @Override
    public Agent mapRow(ResultSet rs, int rowNum) throws SQLException {
        int agentId = rs.getInt("agent_id");
        String agencyName = rs.getString("agency_name");
        int yearsOfExperience = rs.getInt("years_of_experience");
        String playersCurrentlyRepresenting = rs.getString("players_currently_representing");
        String teamAssociations = rs.getString("team_associations");
        int rating = rs.getInt("rating");
        Agent agent = new Agent(agentId, agencyName, yearsOfExperience, playersCurrentlyRepresenting, teamAssociations, rating);
        return agent;
    }
};

    public JdbcAgentDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Agent> getAllAgents() {
        return template.query("SELECT * FROM agent", mapper);
    }

    @Override
    public Agent getAgentById(int agentId) {
        String sql = "SELECT * FROM agent WHERE agent_id = ?";
        return template.queryForObject(sql, mapper, agentId);
    }

    @Override
    public Agent addAgent(Agent agent) {
        Agent newAgent = null;

        String sql = "INSERT INTO agent (agency_name, years_of_experience, players_currently_representing, team_associations, rating) " +
                "VALUES (?, ?, ?, ?, ?) returning agent_id";

        try {
            Integer agentId = template.queryForObject(sql, int.class, agent.getAgencyName(), agent.getYearsOfExperience(), agent.getPlayersCurrentlyRepresenting(),
            agent.getTeamAssociations(), agent.getRating());
            newAgent = getAgentById(agentId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newAgent;


    }
    @Override
    public Agent updateAgent(int id, Agent agent) {
       Agent updatedAgent = null;

        String sql = "UPDATE agent SET agency_name = ?, years_of_experience = ?, players_currently_representing = ?, " +
                "team_associations = ?, rating = ? WHERE agent_id = ?";

      try {
          int rowsAffected = template.update(sql, agent.getAgencyName(), agent.getYearsOfExperience(), agent.getPlayersCurrentlyRepresenting(),
                  agent.getTeamAssociations(), agent.getRating(), id);
          if (rowsAffected == 0){
              throw new DaoException("Zero rows affected, expected at least one");
          }
          updatedAgent = getAgentById(id);
      } catch (CannotGetJdbcConnectionException e) {
          throw new DaoException("Unable to connect to server or database", e);
      } catch (DataIntegrityViolationException e) {
          throw new DaoException("Data integrity violation", e);
      }

        return updatedAgent;

    }

    @Override
    public void deleteAgent(int id) {
        template.update("DELETE FROM agent WHERE agent_id = ?", id);

    }

}
