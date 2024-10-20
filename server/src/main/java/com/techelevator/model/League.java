package com.techelevator.model;

public class League {

    private String leagueName;
    private final int countryId;
    private int numberOfTeamsInLeague;
    private int leagueDivision;

    public League(String leagueName, int countryId, int numberOfTeamsInLeague, int leagueDivision) {
        this.leagueName = leagueName;
        this.countryId = countryId;
        this.numberOfTeamsInLeague = numberOfTeamsInLeague;
        this.leagueDivision = leagueDivision;
    }

   /* public int getLeagueId() {
        return leagueId;
    }*/

    public String getLeagueName() {
        return leagueName;
    }


    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
    public int getCountryId() {
        return countryId;
    }

    public void setLeagueDivision(int leagueDivision) {
        this.leagueDivision = leagueDivision;
    }

    // SET setters in this class!!!! For the chance that we may need to update the League.


    public int getNumberOfTeamsInLeague() {
        return numberOfTeamsInLeague;
    }

    public void setNumberOfTeamsInLeague(int numberOfTeamsInLeague) {
        this.numberOfTeamsInLeague = numberOfTeamsInLeague;
    }

    public int getLeagueDivision() {
        return leagueDivision;
    }


    @Override
    public String toString() {
        return "League|" +
                "league Name= '" + leagueName + '\'' +
                ", country Id= " + countryId +
                ", number Of Teams In League= " + numberOfTeamsInLeague +
                ", league Division= " + leagueDivision +
                '|';
    }
}
