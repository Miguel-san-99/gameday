package com.soccerapp.gameday.dtos;

import java.time.LocalDate;

public class RegisterMatchdayRequest {
    private String name;
    private LocalDate date;
    private Long tournamentId;

    public RegisterMatchdayRequest() {
    }

    public RegisterMatchdayRequest(String name, LocalDate date, Long tournamentId) {
        this.name = name;
        this.date = date;
        this.tournamentId = tournamentId;
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
}
