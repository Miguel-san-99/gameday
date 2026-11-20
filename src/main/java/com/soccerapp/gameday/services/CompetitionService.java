package com.soccerapp.gameday.services;

import com.soccerapp.gameday.dtos.CompetitionDto;
import com.soccerapp.gameday.exceptions.BusinessException;
import com.soccerapp.gameday.exceptions.DuplicateResourceException;
import com.soccerapp.gameday.exceptions.ResourceNotFoundException;
import com.soccerapp.gameday.mappers.CompetitionMapper;
import com.soccerapp.gameday.models.Competition;
import java.util.List;

import org.springframework.stereotype.Service;
import com.soccerapp.gameday.repositories.CompetitionRepository;
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
        return competitionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la competencia con id: " + id));
    }

    @Transactional(readOnly = true)
    public CompetitionDto getCompetitionDtoById(Long id) {
        return competitionMapper.toDto(getCompetitionById(id));
    }

    @Transactional(readOnly = true)
    public Competition getCompetitionByName(String name) {
        return competitionRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("No existe la competencia con nombre: " + name));
    }
    
    @Transactional
    public Competition saveCompetition(CompetitionDto request) {
        if (request.getName().isBlank()) {
            throw new BusinessException("No puedes dejar el nombre de la competencia en blanco.", "/admin/tournament");
        }
        if (!competitionRepository.findByName(request.getName()).isEmpty()) {
            throw new DuplicateResourceException("Ya existe una competencia con este nombre.", "/admin/tournament");
        }
        Competition competition = competitionMapper.toEntity(request);
        return competitionRepository.save(competition);
    }
}