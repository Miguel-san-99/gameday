package com.soccerapp.gameday.services;

import com.soccerapp.gameday.dtos.CompetitionDto;
import com.soccerapp.gameday.mappers.CompetitionMapper;
import com.soccerapp.gameday.models.Competition;
import java.util.List;

import org.springframework.stereotype.Service;
import com.soccerapp.gameday.repositories.CompetitionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompetitionService {
    
    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;

    public CompetitionService(CompetitionRepository competitionRepository, CompetitionMapper competitionMapper) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
    }
    
    @Transactional(readOnly = true)
    public List<CompetitionDto> getAllCompetitions() {
        return competitionMapper.toDtoList(competitionRepository.findAll());
    }
    
    @Transactional(readOnly = true)
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe la competencia con id: " + id));
    }

    @Transactional(readOnly = true)
    public CompetitionDto getCompetitionDtoById(Long id) {
        Competition competition = competitionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe la competencia con id: " + id));
        return competitionMapper.toDto(competition);
    }
    
    @Transactional
    public Competition saveCompetition(CompetitionDto request) {
        if (request == null) {
            throw new IllegalArgumentException("La competencia no puede ser null.");
        }
        return competitionRepository.save(competitionMapper.toEntity(request));
    }
}