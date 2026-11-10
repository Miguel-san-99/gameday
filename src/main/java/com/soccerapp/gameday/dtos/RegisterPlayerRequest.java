package com.soccerapp.gameday.dtos;

import java.time.LocalDate;

public class RegisterPlayerRequest {
    private String name;
    private Long teamId;
    private LocalDate birthDate;

    public RegisterPlayerRequest() {
    }

    public RegisterPlayerRequest(String name, Long teamId, LocalDate birthDate) {
        this.name = name;
        this.teamId = teamId;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}