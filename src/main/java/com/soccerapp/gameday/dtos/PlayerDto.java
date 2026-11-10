package com.soccerapp.gameday.dtos;

import java.time.LocalDate;

public class PlayerDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Long teamId;

    public PlayerDto() {
    }

    public PlayerDto(Long id, String name, LocalDate birthDate, Long teamId) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.teamId = teamId;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
