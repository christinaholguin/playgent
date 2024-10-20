package com.techelevator.controller;


import com.techelevator.dao.AthleteDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Athlete;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/athletes")
//@PreAuthorize("isAuthenticated()")
public class AthleteController {

    private final AthleteDao athleteDao;
    private final UserDao userDao;

    public AthleteController(AthleteDao athleteDao, UserDao userDao) {
        this.athleteDao = athleteDao;
        this.userDao = userDao;
    }


  //  @RequestMapping(method = RequestMethod.GET)
    //Gets a List of all Athletes. Anyone
    @GetMapping
    public List<Athlete> getAllAthletes(){
        return athleteDao.getAllAthletes();
    }


    // This method creates an Athlete. Can be done by AGENT or ADMIN
   @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Athlete createAthlete(@RequestBody Athlete newAthlete) {
      return athleteDao.addAthlete(newAthlete);

    }


    //Gets an Athlete by their ID
    @GetMapping("/{athleteId}")
    public Athlete getAthleteById(@PathVariable int athleteId) {
        Athlete athlete = athleteDao.getAthleteById(athleteId);
        if (athlete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Athlete not found");
        }
        return athlete;
    }


    // Updates an Athlete. Can only be done by an AGENT or ADMIN
    @PutMapping("/{id}")
   @PreAuthorize("hasAnyRole('AGENT', 'ADMIN')")
    public Athlete updateAthlete(@PathVariable int id, @RequestBody Athlete athlete) {
        return athleteDao.updateAthlete(id, athlete);

    }

    // Deletes an Athlete can ONLY BE DONE BY ADMIN
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAthlete(@PathVariable int id) {
       athleteDao.deleteAthlete(id);

    }

}
