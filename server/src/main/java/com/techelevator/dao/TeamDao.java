package com.techelevator.dao;

import com.techelevator.model.Team;

import java.util.List;

public interface TeamDao {
    List<Team> getAllTeams();
    Team getTeamByName(String teamName);
    Team updateTeam(String name, Team team);
    void deleteTeam(String name);
}
