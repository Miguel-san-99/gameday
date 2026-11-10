package com.soccerapp.gameday.dtos;

import java.util.List;

public class CompetitionDto {
    private Long id;
    private String name;
    private List<TournamentDto> tournaments;

    public CompetitionDto() {
    }

    public CompetitionDto(Long id, String name, List<TournamentDto> tournaments) {
        this.id = id;
        this.name = name;
        this.tournaments = tournaments;
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

    public List<TournamentDto> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TournamentDto> tournaments) {
        this.tournaments = tournaments;
    }
}
