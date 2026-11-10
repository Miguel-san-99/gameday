package com.soccerapp.gameday.dtos;

import java.time.LocalTime;

public class RegisterMatchRequest {
    private Long homeTeamId;
    private Long awayTeamId;
    private LocalTime date;
    private Long matchdayId;

    public RegisterMatchRequest() {
    }

    public RegisterMatchRequest(Long homeTeamId, Long awayTeamId, LocalTime date, Long matchdayId) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.date = date;
        this.matchdayId = matchdayId;
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

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }

    public Long getMatchdayId() {
        return matchdayId;
    }

    public void setMatchdayId(Long matchdayId) {
        this.matchdayId = matchdayId;
    }
}
