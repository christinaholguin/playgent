package com.techelevator.dao;

import com.techelevator.model.Country;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcCountryDao implements CountryDao {

private final JdbcTemplate template;

    private RowMapper<Country> mapper = new RowMapper<Country>(){
        @Override
        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
            int countryId = rs.getInt("country_id");
            String countryName = rs.getString("country_name");
            Country country = new Country(countryId,countryName);
            return country;
        }
    };

    public JdbcCountryDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Country> getAllCountries() {
        return template.query("SELECT * FROM country", mapper);
    }
}
