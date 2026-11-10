package com.soccerapp.gameday.services;

import com.soccerapp.gameday.dtos.PlayerDto;
import com.soccerapp.gameday.dtos.RegisterPlayerRequest;
import com.soccerapp.gameday.mappers.PlayerMapper;
import com.soccerapp.gameday.models.Player;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.repositories.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, TeamService teamService, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
        this.playerMapper = playerMapper;
    }
    
    @Transactional(readOnly = true)
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe el jugador con id: " + id));
    }
    
    @Transactional
    public PlayerDto savePlayer(RegisterPlayerRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El jugador no puede ser null.");
        }
        Team team = teamService.getTeamById(request.getTeamId());
        Player player = playerMapper.toEntity(request, team);
        return playerMapper.toDto(playerRepository.save(player));
    }
}
