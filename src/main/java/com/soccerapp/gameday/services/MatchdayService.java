package com.soccerapp.gameday.services;

import com.soccerapp.gameday.dtos.MatchdayDto;
import com.soccerapp.gameday.dtos.RegisterMatchdayRequest;
import com.soccerapp.gameday.exceptions.BusinessException;
import com.soccerapp.gameday.exceptions.InvalidRequestException;
import com.soccerapp.gameday.exceptions.ResourceNotFoundException;
import com.soccerapp.gameday.mappers.MatchdayMapper;
import com.soccerapp.gameday.models.Matchday;
import com.soccerapp.gameday.models.Tournament;
import com.soccerapp.gameday.repositories.MatchdayRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class MatchdayService {
    
    private final MatchdayRepository matchdayRepository;
    private final MatchdayMapper matchdayMapper;
    private final TournamentService tournamentService;

    public MatchdayService(MatchdayRepository matchdayRepository, MatchdayMapper matchdayMapper, TournamentService tournamentService) {
        this.matchdayRepository = matchdayRepository;
        this.matchdayMapper = matchdayMapper;
        this.tournamentService = tournamentService;
    }
    
    @Transactional(readOnly = true)
    public Matchday getMatchdayById(Long id) {
        if (id == null) {
            throw new InvalidRequestException("El ID de la jornada es obligatorio.");
        }
        return matchdayRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la jornada con id: " + id));
    }

    @Transactional(readOnly = true)
    public MatchdayDto getMatchdayDtoById(Long id) {
        return matchdayMapper.toDto(getMatchdayById(id));
    }

    @Transactional
    public MatchdayDto saveMatchday(RegisterMatchdayRequest request) {
        if (request.getName().isBlank()) {
            throw new BusinessException("No puedes dejar el nombre en blanco.", "/admin/tournament/" + request.getTournamentId());
        }
        if (request.getDate().isBefore(LocalDate.now())) {
            throw new BusinessException("No puedes seleccionar una fecha anterior al dia de hoy.", "/admin/tournament/" + request.getTournamentId());
        }
        Tournament tournament = tournamentService.getTournamentById(request.getTournamentId());
        Matchday matchday = matchdayMapper.toEntity(request, tournament);
        return matchdayMapper.toDto(matchdayRepository.save(matchday));
    }

}