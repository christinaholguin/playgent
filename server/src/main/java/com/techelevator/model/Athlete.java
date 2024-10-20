package com.techelevator.model;

import java.math.BigDecimal;
import java.util.Date;

public class Athlete {

    private int athleteId;
    private String name;
    private Date dob;
    private BigDecimal height;
    private String nationality;
    private String visa;
    private String positionPlayed;
    private int currentTeamId;
    private Date currentContractExpiration;
    private int residingCountryId;

    public Athlete(int athleteId, String name, Date dob, BigDecimal height, String nationality, String visa, String positionPlayed, int currentTeamId, Date currentContractExpiration, int residingCountryId) {
        this.athleteId = athleteId;
        this.name = name;
        this.dob = dob;
        this.height = height;
        this.nationality = nationality;
        this.visa = visa;
        this.positionPlayed = positionPlayed;
        this.currentTeamId = currentTeamId;
        this.currentContractExpiration = currentContractExpiration;
        this.residingCountryId = residingCountryId;
    }

    public Athlete() {

    }

    public int getAthleteId() {
        return athleteId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getPositionPlayed() {
        return positionPlayed;
    }

    public void setPositionPlayed(String positionPlayed) {
        this.positionPlayed = positionPlayed;
    }

    public int getCurrentTeamId() {
        return currentTeamId;
    }


    public Date getCurrentContractExpiration() {
        return currentContractExpiration;
    }

    public void setCurrentContractExpiration(Date currentContractExpiration) {
        this.currentContractExpiration = currentContractExpiration;
    }

    public int getResidingCountryId() {
        return residingCountryId;
    }


    @Override
    public String toString() {
        return "Athlete|" +
                "athlete Id= " + athleteId +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", height=" + height +
                ", nationality='" + nationality + '\'' +
                ", visa='" + visa + '\'' +
                ", position played='" + positionPlayed + '\'' +
                ", current team id= " + currentTeamId +
                ", current contract expiration= " + currentContractExpiration +
                ", residing country id= 4" + residingCountryId +
                '|';
    }
}
