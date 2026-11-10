package com.soccerapp.gameday.dtos;

import java.time.LocalTime;
import java.util.List;

public class UpdateMatchRequest {
    private String homeTeamName;
    private String awayTeamName;
    private Integer homeGoals;
    private Integer awayGoals;
    private boolean played;
    private String notes;
    private LocalTime date;
    private List<PlayerMatchStatDto> playersStats;

    public UpdateMatchRequest() {
    }

    public UpdateMatchRequest(String homeTeamName, String awayTeamName, Integer homeGoals, Integer awayGoals, boolean played, String notes, LocalTime date, List<PlayerMatchStatDto> playersStats) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.played = played;
        this.notes = notes;
        this.date = date;
        this.playersStats = playersStats;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }

    public List<PlayerMatchStatDto> getPlayersStats() {
        return playersStats;
    }

    public void setPlayersStats(List<PlayerMatchStatDto> playersStats) {
        this.playersStats = playersStats;
    }
}
