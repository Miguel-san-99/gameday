package com.soccerapp.gameday.dtos;

import org.apache.catalina.LifecycleState;

import java.time.LocalTime;
import java.util.List;

public class MatchDto {
    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private Integer homeGoals;
    private Integer awayGoals;
    private LocalTime date;
    private boolean played;
    private String notes;
    private List<PlayerMatchStatDto> playerMatchStats;

    public MatchDto() {
    }

    public MatchDto(Long id, Long homeTeamId, Long awayTeamId, String homeTeamName, String awayTeamName, Integer homeGoals, Integer awayGoals, LocalTime date, boolean played, String notes, List<PlayerMatchStatDto> playerMatchStats) {
        this.id = id;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.date = date;
        this.played = played;
        this.notes = notes;
        this.playerMatchStats = playerMatchStats;
    }

    public Long getId() {
        return id;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
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

    public void setId(Long id) {
        this.id = id;
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

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
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

    public List<PlayerMatchStatDto> getPlayerMatchStats() {
        return playerMatchStats;
    }

    public void setPlayerMatchStats(List<PlayerMatchStatDto> playerMatchStats) {
        this.playerMatchStats = playerMatchStats;
    }
}
