package com.soccerapp.gameday.dtos;

import java.time.LocalDate;
import java.util.List;

public class MatchdayDto {
    private Long id;
    private String name;
    private LocalDate date;
    private Long tournamentId;
    private List<MatchDto> matches;

    public MatchdayDto() {
    }

    public MatchdayDto(Long id, String name, LocalDate date, Long tournamentId, List<MatchDto> matches) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.tournamentId = tournamentId;
        this.matches = matches;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public List<MatchDto> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchDto> matches) {
        this.matches = matches;
    }
}
