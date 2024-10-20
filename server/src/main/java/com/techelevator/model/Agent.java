package com.techelevator.model;

public class Agent {
    private final int agentId;
    private final String agencyName;
    private int yearsOfExperience;
    private String playersCurrentlyRepresenting;
    private String teamAssociations;
    private int rating;



    public Agent(int agentId, String agencyName, int yearsOfExperience, String playersCurrentlyRepresenting, String teamAssociations, int rating) {
        this.agentId = agentId;
        this.agencyName = agencyName;
        this.yearsOfExperience = yearsOfExperience;
        this.playersCurrentlyRepresenting = playersCurrentlyRepresenting;
        this.teamAssociations = teamAssociations;
        this.rating = rating;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setPlayersCurrentlyRepresenting(String playersCurrentlyRepresenting) {
        this.playersCurrentlyRepresenting = playersCurrentlyRepresenting;
    }

    public void setTeamAssociations(String teamAssociations) {
        this.teamAssociations = teamAssociations;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getAgentId() {
        return agentId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getPlayersCurrentlyRepresenting() {
        return playersCurrentlyRepresenting;
    }

    public String getTeamAssociations() {
        return teamAssociations;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Agent|" +
                "agent Id= " + agentId +
                ", agency name= '" + agencyName + '\'' +
                ", years Of Experience= " + yearsOfExperience +
                ", players Currently Representing= '" + playersCurrentlyRepresenting + '\'' +
                ", team Associations= '" + teamAssociations + '\'' +
                ", rating= " + rating +
                '|';
    }
}
