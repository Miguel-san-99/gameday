package com.soccerapp.gameday.mappers;

import com.soccerapp.gameday.dtos.TournamentDto;
import com.soccerapp.gameday.models.Tournament;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TournamentMapper {
    private final MatchdayMapper matchdayMapper;
    private final TeamMapper teamMapper;

    public TournamentMapper(MatchdayMapper matchdayMapper, TeamMapper teamMapper) {
        this.matchdayMapper = matchdayMapper;
        this.teamMapper = teamMapper;
    }

    public TournamentDto toDto(Tournament tournament) {
        TournamentDto dto = new TournamentDto();
        dto.setId(tournament.getId());
        dto.setName(tournament.getName());
        dto.setCompetitionId(tournament.getCompetition().getId());
        dto.setCompetitionName(tournament.getCompetition().getName());
        dto.setMatchdays(matchdayMapper.toDtoList(tournament.getMatchdays()));
        dto.setTeams(teamMapper.toDtoList(tournament.getTeams()));
        return dto;
    }

    public List<TournamentDto> toDtoList(List<Tournament> tournaments) {
        return tournaments.stream().map(this::toDto).toList();
    }
}