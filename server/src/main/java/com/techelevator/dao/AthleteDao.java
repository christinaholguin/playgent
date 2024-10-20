package com.techelevator.dao;

import com.techelevator.model.Athlete;

import java.util.List;

public interface AthleteDao {
    List<Athlete> getAllAthletes();
    Athlete getAthleteById(int athleteId);
    Athlete addAthlete(Athlete newAthlete);
    Athlete updateAthlete(int id, Athlete athlete);
    void deleteAthlete(int id);
}
