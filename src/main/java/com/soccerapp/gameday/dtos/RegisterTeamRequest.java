package com.soccerapp.gameday.dtos;

public class RegisterTeamRequest {
    private String name;
    private String managerName;
    private String password;
    private Long tournamentId;

    public RegisterTeamRequest() {
    }

    public RegisterTeamRequest(String name, String managerName, String password, Long tournamentId) {
        this.name = name;
        this.managerName = managerName;
        this.password = password;
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }
}
