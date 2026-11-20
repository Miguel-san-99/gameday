package com.soccerapp.gameday.services;

import com.soccerapp.gameday.dtos.RegisterTeamRequest;
import com.soccerapp.gameday.dtos.RegisterUserRequest;
import com.soccerapp.gameday.dtos.TeamDto;
import com.soccerapp.gameday.dtos.UpdateTeamRequest;
import com.soccerapp.gameday.enums.TeamStatus;
import com.soccerapp.gameday.exceptions.InvalidRequestException;
import com.soccerapp.gameday.exceptions.ResourceNotFoundException;
import com.soccerapp.gameday.mappers.TeamMapper;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.models.Tournament;
import com.soccerapp.gameday.models.User;
import com.soccerapp.gameday.repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {
    
    private final TeamRepository teamRepository;
    private final UserService userService;
    private final TeamMapper teamMapper;
    private final TournamentService tournamentService;

    public TeamService(TeamRepository teamRepository, UserService userService, TeamMapper teamMapper, TournamentService tournamentService) {
        this.teamRepository = teamRepository;
        this.userService = userService;
        this.teamMapper = teamMapper;
        this.tournamentService = tournamentService;
    }
    
    @Transactional(readOnly = true)
    public Team getTeamById(Long id) {
        if (id == null) {
            throw new InvalidRequestException("El ID del equipo es obligatorio.");
        }
        return teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el equipo con el id: " + id));
    }

    public TeamDto getTeamDtoById(Long id) {
        return teamMapper.toDto(getTeamById(id));
    }

    @Transactional
    public Team saveTeam(RegisterTeamRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El equipo no puede ser null.");
        }
        Tournament tournament = tournamentService.getTournamentById(request.getTournamentId());
        Team team = teamMapper.toEntity(request, tournament);
        RegisterUserRequest managerRequest = new RegisterUserRequest();
        managerRequest.setName(request.getManagerName());
        managerRequest.setPassword(request.getPassword());
        User manager = userService.saveUserManager(managerRequest, request.getTournamentId());
        team.setManager(manager);
        return teamRepository.save(team);
    }

    @Transactional
    public Team updateTeam(Long id, UpdateTeamRequest request) {
        Team team = this.getTeamById(id);
        team.setName(request.getName());
        team.setStatus(request.getStatus());
        userService.updateUser(team.getManager().getId(), request.getManagerName(), request.getManagerPassword());
        return teamRepository.save(team);
    }

    public UpdateTeamRequest setUpdateRequest(Long teamId) {
        return teamMapper.toUpdateTeamRequest(this.getTeamById(teamId));
    }
}