package com.techelevator.controller;


import com.techelevator.dao.TeamDao;
import com.techelevator.model.Team;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teams")
/*@PreAuthorize("isAuthenticated()")*/
public class TeamController {

    private final TeamDao teamDao;

    public TeamController(TeamDao teamDao) {
        this.teamDao = teamDao;
    }


    // Allows anyone to get a List of all Teams
    @GetMapping
    public List<Team> getAllTeams() {
        return teamDao.getAllTeams();
    }

    // Gets a Team by their Name
    @GetMapping("/{name}")
    public Team getTeamByName(@PathVariable String name) {
        Team team = teamDao.getTeamByName(name);
        if (team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }
        return team;
    }


    // Deletes a Team from the database. can ONLY BE DONE BY ADMIN
    @DeleteMapping("/{name}")
   @PreAuthorize("hasRole('ADMIN')")
    public void deleteTeam(@PathVariable String name) {
        teamDao.deleteTeam(name);
    }


    // Updates a Team. can ONLY BE DONE BY ADMIN
    @PutMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public Team updateTeam(@PathVariable String name , @RequestBody Team updatedTeam) {
        return teamDao.updateTeam(name, updatedTeam);

    }
}
