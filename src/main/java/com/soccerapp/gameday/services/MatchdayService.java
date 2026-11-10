package com.soccerapp.gameday.services;

import com.soccerapp.gameday.dtos.MatchdayDto;
import com.soccerapp.gameday.dtos.RegisterMatchdayRequest;
import com.soccerapp.gameday.mappers.MatchdayMapper;
import com.soccerapp.gameday.models.Matchday;
import com.soccerapp.gameday.models.Tournament;
import com.soccerapp.gameday.repositories.MatchdayRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Transactional
    public MatchdayDto saveMatchday(RegisterMatchdayRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("La jornada no puede ser null.");
        }
        Tournament tournament = tournamentService.getTournamentById(request.getTournamentId());
        Matchday matchday = matchdayMapper.toEntity(request, tournament);
        return matchdayMapper.toDto(matchdayRepository.save(matchday));
    }
    
    @Transactional(readOnly = true)
    public Matchday getMatchdayById(Long id) {
        return matchdayRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe la jornada con id: " + id));
    }

    @Transactional(readOnly = true)
    public MatchdayDto getMatchdayDtoById(Long id) {
        Matchday matchday = matchdayRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe la jornada con id: " + id));
        return matchdayMapper.toDto(matchday);
    }
}