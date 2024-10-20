package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Athlete;
import net.sf.jsqlparser.parser.feature.Feature;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component
public class JdbcAthleteDao implements AthleteDao{

    private final JdbcTemplate template;

    private RowMapper<Athlete> mapper = new RowMapper<Athlete>() {
        @Override
        public Athlete mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer athleteId = rs.getInt("athlete_id");
            String name = rs.getString("name");
            Date dob = rs.getDate("dob");
            BigDecimal height = rs.getBigDecimal("height");
            String nationality = rs.getString("nationality");
            String visa = rs.getString("visa");
            String positionPlayed = rs.getString("position_played");
            Integer currentTeamId = rs.getInt("current_team_id");
            Date currentContractExpiration = rs.getDate("current_contract_expiration");
            Integer residingCountryId = rs.getInt("residing_country_id");
            Athlete athlete = new Athlete( athleteId, name, dob,height, nationality, visa, positionPlayed, currentTeamId, currentContractExpiration,residingCountryId);
            return athlete;
        }
    };

    public JdbcAthleteDao(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Athlete> getAllAthletes() {
        return template.query("SELECT * FROM athlete", mapper);
    }

    @Override
    public Athlete getAthleteById(int athleteId) {
        String sql = "SELECT athlete_id, name, dob, height, nationality, visa, position_played, current_team_id, " +
                "current_contract_expiration, residing_country_id FROM athlete WHERE athlete_id = ?";
        return template.queryForObject(sql, mapper, athleteId);
    }

    @Override
    public Athlete addAthlete(Athlete athlete) {
       Athlete newAthlete = null;

       String sql = "INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, " +
               "current_contract_expiration, residing_country_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) returning athlete_id";

        try {
            Integer athleteId = template.queryForObject(sql, int.class, athlete.getName(), athlete.getDob(), athlete.getHeight(), athlete.getNationality(),
                    athlete.getVisa(), athlete.getPositionPlayed(), athlete.getCurrentTeamId(), athlete.getCurrentContractExpiration(), athlete.getResidingCountryId());
            newAthlete = getAthleteById(athleteId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newAthlete;

    }

    @Override
    public Athlete updateAthlete(int id, Athlete athlete) {

        Athlete updatedAthlete = null;
        String sql = "UPDATE athlete SET name = ?, dob = ?, height = ?, nationality = ?, visa = ?, position_played = ?, current_team_id = ?, " +
                "current_contract_expiration = ?, residing_country_id = ? WHERE athlete_id = ?";

        try{
            int rowsAffected = template.update(sql, athlete.getName(), athlete.getDob(), athlete.getHeight(), athlete.getNationality(), athlete.getVisa(),
                    athlete.getPositionPlayed(), athlete.getCurrentTeamId(), athlete.getCurrentContractExpiration(), athlete.getResidingCountryId(), id);
            if (rowsAffected == 0){
                throw new DaoException("Zero rows affected, expected at least 1");
            }
            updatedAthlete = getAthleteById(athlete.getAthleteId());
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }

        return updatedAthlete;

    }

    @Override
    public void deleteAthlete(int id) {
        template.update("DELETE FROM athlete WHERE athlete_id = ?", id);

    }



 //   @Override
  //  public List<Athlete> getAllAthletes() {

   //     List<Athlete> athletes = new ArrayList<>();

   //     String sql = "SELECT * FROM athlete";

    //    SqlRowSet rs = template.queryForRowSet(sql);

    //    while(rs.next()){Athlete athlete = mapRowToEntry(rs);

    //        athletes.add(athlete);
    //    }

     //   return athletes;
   // }

   // private static Athlete mapRowToEntry(SqlRowSet rs) {
   //     Integer athleteId = rs.getInt("athlete_id");
    //    String name = rs.getString("name");
    //    Date dob = rs.getDate("dob");
    //    BigDecimal height = rs.getBigDecimal("height");
    //    String nationality = rs.getString("nationality");
    //    String visa = rs.getString("visa");
    //    String positionPlayed = rs.getString("position_played");
     //   Integer currentTeamId = rs.getInt("current_team_id");
     //   Date currentContractExpiration = rs.getDate("current_contract_expiration");
    //    Integer residingCountryId = rs.getInt("residing_country_id");
   //     Athlete athlete = new Athlete( athleteId, name, dob,height, nationality, visa, positionPlayed, currentTeamId, currentContractExpiration,residingCountryId);
    //    return athlete;
  //  }
}
