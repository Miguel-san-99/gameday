package com.soccerapp.gameday.mappers;

import com.soccerapp.gameday.dtos.CompetitionDto;
import com.soccerapp.gameday.models.Competition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompetitionMapper {
    private final TournamentMapper tournamentMapper;

    public CompetitionMapper(TournamentMapper tournamentMapper) {
        this.tournamentMapper = tournamentMapper;
    }

    public CompetitionDto toDto(Competition competition) {
        CompetitionDto dto = new CompetitionDto();
        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setTournaments(tournamentMapper.toDtoList(competition.getTournaments()));
        return dto;
    }

    public Competition toEntity(CompetitionDto competitionDto) {
        Competition competition = new Competition();
        competition.setName(competitionDto.getName());
        return competition;
    }

    public List<CompetitionDto> toDtoList(List<Competition> competitions) {
        return competitions.stream().map(this::toDto).toList();
    }
}
