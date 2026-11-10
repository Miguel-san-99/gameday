package com.soccerapp.gameday.mappers;

import com.soccerapp.gameday.dtos.RegisterTeamRequest;
import com.soccerapp.gameday.dtos.TeamDto;
import com.soccerapp.gameday.dtos.UpdateTeamRequest;
import com.soccerapp.gameday.enums.TeamStatus;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.models.Tournament;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamMapper {
    private final PlayerMapper playerMapper;

    public TeamMapper(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    public UpdateTeamRequest toUpdateTeamRequest(Team team) {
        UpdateTeamRequest teamDto = new UpdateTeamRequest();
        teamDto.setName(team.getName());
        teamDto.setStatus(team.getStatus());
        teamDto.setManagerName(team.getManager().getName());
        return teamDto;
    }

    public TeamDto toDto(Team team) {
        TeamDto dto = new TeamDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setStatus(team.getStatus());
        dto.setManagerId(team.getManager().getId());
        dto.setManagerName(team.getManager().getName());
        dto.setTournamentId(team.getTournament().getId());
        dto.setPlayers(playerMapper.toDtoList(team.getPlayers()));
        return dto;
    }

    public List<TeamDto> toDtoList(List<Team> teams) {
        return teams.stream().map(this::toDto).toList();
    }

    public Team toEntity(RegisterTeamRequest request, Tournament tournament) {
        Team team = new Team();
        team.setName(request.getName());
        team.setStatus(TeamStatus.ACTIVE);
        team.setTournament(tournament);
        return team;
    }
}