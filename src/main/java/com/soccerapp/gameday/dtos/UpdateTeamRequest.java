package com.soccerapp.gameday.dtos;

import com.soccerapp.gameday.enums.TeamStatus;

public class UpdateTeamRequest {
    private String name;
    private TeamStatus status;
    private String managerName;
    private String managerPassword;

    public UpdateTeamRequest() {
    }

    public UpdateTeamRequest(String name, TeamStatus status, String managerName, String managerPassword) {
        this.name = name;
        this.status = status;
        this.managerName = managerName;
        this.managerPassword = managerPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }
}
