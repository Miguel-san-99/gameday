package com.soccerapp.gameday.dtos;

public class RegisterTournamentRequest {
    private String name;
    private Long competitionId;

    public RegisterTournamentRequest() {
    }

    public RegisterTournamentRequest(String name, Long competitionId) {
        this.name = name;
        this.competitionId = competitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }
    
}