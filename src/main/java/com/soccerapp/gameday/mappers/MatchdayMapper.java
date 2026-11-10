package com.soccerapp.gameday.mappers;

import com.soccerapp.gameday.dtos.MatchdayDto;
import com.soccerapp.gameday.dtos.RegisterMatchdayRequest;
import com.soccerapp.gameday.models.Matchday;
import com.soccerapp.gameday.models.Tournament;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchdayMapper {
    private final MatchMapper matchMapper;

    public MatchdayMapper(MatchMapper matchMapper) {
        this.matchMapper = matchMapper;
    }

    public Matchday toEntity(RegisterMatchdayRequest matchdayRequest, Tournament tournament) {
        Matchday matchday = new Matchday();
        matchday.setName(matchdayRequest.getName());
        matchday.setDate(matchdayRequest.getDate());
        matchday.setTournament(tournament);
        return matchday;
    }

    public MatchdayDto toDto(Matchday matchday) {
        MatchdayDto dto = new MatchdayDto();
        dto.setId(matchday.getId());
        dto.setName(matchday.getName());
        dto.setDate(matchday.getDate());
        dto.setTournamentId(matchday.getTournament().getId());
        dto.setMatches(matchMapper.toDtoList(matchday.getMatches()));
        return dto;
    }

    public List<MatchdayDto> toDtoList(List<Matchday> matchdays) {
        List<MatchdayDto> matchdaysDto = matchdays.stream().map(this::toDto).toList();
        return matchdaysDto;
    }
}
