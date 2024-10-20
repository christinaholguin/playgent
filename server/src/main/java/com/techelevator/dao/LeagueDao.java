package com.techelevator.dao;

import com.techelevator.model.League;

import java.util.List;

public interface LeagueDao {
    List<League> getAllLeagues();

    League getLeagueByName(String name);

}
