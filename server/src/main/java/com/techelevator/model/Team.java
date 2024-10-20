package com.techelevator.model;

public class Team {

    private final String teamName;
    private final int countryId;
    private final int rankFromPreviousSeason;
    private final int numberOfPlayersOnTeam;
    private final int numberOfLeagueTitlesWon;

    public Team(String teamName, int countryId, int rankFromPreviousSeason, int numberOfPlayersOnTeam, int numberOfLeagueTitlesWon) {
        this.teamName = teamName;
        this.countryId = countryId;
        this.rankFromPreviousSeason = rankFromPreviousSeason;
        this.numberOfPlayersOnTeam = numberOfPlayersOnTeam;
        this.numberOfLeagueTitlesWon = numberOfLeagueTitlesWon;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getCountryId() {
        return countryId;
    }

    public int getRankFromPreviousSeason() {
        return rankFromPreviousSeason;
    }

    public int getNumberOfPlayersOnTeam() {
        return numberOfPlayersOnTeam;
    }

    public int getNumberOfLeagueTitlesWon() {
        return numberOfLeagueTitlesWon;
    }

    @Override
    public String toString() {
        return "Team|" +
                "team name= '" + teamName + '\'' +
                ", country id= " + countryId +
                ", rank from previous season= " + rankFromPreviousSeason +
                ", number of players on team= " + numberOfPlayersOnTeam +
                ", number of league titles won= " + numberOfLeagueTitlesWon +
                '|';
    }
}
