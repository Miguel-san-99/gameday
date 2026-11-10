package com.soccerapp.gameday.dtos;

import java.util.List;

public class TournamentDto {
    private Long id;
    private String name;
    private Long competitionId;
    private String competitionName;
    private List<MatchdayDto> matchdays;
    private List<TeamDto> teams;

    public TournamentDto() {
    }

    public TournamentDto(Long id, String name, Long competitionId, String competitionName, List<MatchdayDto> matchdays, List<TeamDto> teams) {
        this.id = id;
        this.name = name;
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.matchdays = matchdays;
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public List<MatchdayDto> getMatchdays() {
        return matchdays;
    }

    public void setMatchdays(List<MatchdayDto> matchdays) {
        this.matchdays = matchdays;
    }

    public List<TeamDto> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDto> teams) {
        this.teams = teams;
    }
}
