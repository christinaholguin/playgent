package com.techelevator.controller;


import com.techelevator.dao.LeagueDao;
import com.techelevator.model.League;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/leagues")
/*@PreAuthorize("isAuthenticated()")*/
public class LeagueController {

    private final LeagueDao leagueDao;

    public LeagueController(LeagueDao leagueDao) {
        this.leagueDao = leagueDao;
    }

    // Allows anyone to get a list of All Leagues in database
    @GetMapping
    public List<League> getAllLeagues(){
        return leagueDao.getAllLeagues();
    }

    @GetMapping("/{name}")
    public League getLeagueByName(@PathVariable String name) {
    League league = leagueDao.getLeagueByName(name);
    if(league == null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "League not Found");
    }
    return league;
    }

}
