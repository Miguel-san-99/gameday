package com.soccerapp.gameday.dtos;

public class RegisterCompetitionRequest {
    private String name;

    public RegisterCompetitionRequest() {
    }

    public RegisterCompetitionRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
