package com.soccerapp.gameday.mappers;

import com.soccerapp.gameday.dtos.PlayerDto;
import com.soccerapp.gameday.dtos.RegisterPlayerRequest;
import com.soccerapp.gameday.models.Player;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.services.TeamService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerMapper {

    public Player toEntity(RegisterPlayerRequest request, Team team) {
        Player player = new Player();
        player.setName(request.getName());
        player.setBirthDate(request.getBirthDate());
        player.setTeam(team);
        return player;
    }

    public PlayerDto toDto(Player player) {
        PlayerDto dto = new PlayerDto();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setBirthDate(player.getBirthDate());
        dto.setTeamId(player.getTeam().getId());
        return dto;
    }

    public List<PlayerDto> toDtoList(List<Player> players) {
        return players.stream().map(this::toDto).toList();
    }
}