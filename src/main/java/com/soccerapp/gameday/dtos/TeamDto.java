package com.soccerapp.gameday.dtos;

import com.soccerapp.gameday.enums.TeamStatus;

import java.util.ArrayList;
import java.util.List;

public class TeamDto {
    private Long id;
    private String name;
    private TeamStatus status;
    private Long managerId;
    private String managerName;
    private Long tournamentId;
    private List<PlayerDto> players = new ArrayList<>();

    public TeamDto() {
    }

    public TeamDto(Long id, String name, TeamStatus status, Long managerId, Long tournamentId, String managerName, List<PlayerDto> players) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.managerId = managerId;
        this.tournamentId = tournamentId;
        this.managerName = managerName;
        this.players = players;
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

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }
}