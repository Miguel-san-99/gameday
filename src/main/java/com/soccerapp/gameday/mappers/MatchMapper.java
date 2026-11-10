package com.soccerapp.gameday.mappers;

import com.soccerapp.gameday.dtos.MatchDto;
import com.soccerapp.gameday.dtos.RegisterMatchRequest;
import com.soccerapp.gameday.dtos.UpdateMatchRequest;
import com.soccerapp.gameday.models.Match;
import com.soccerapp.gameday.models.Matchday;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.services.MatchService;
import com.soccerapp.gameday.services.MatchdayService;
import com.soccerapp.gameday.services.TeamService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchMapper {
    private final PlayerMatchStatMapper playerMatchStatMapper;

    public MatchMapper(PlayerMatchStatMapper playerMatchStatMapper) {
        this.playerMatchStatMapper = playerMatchStatMapper;
    }

    public MatchDto toDto(Match match) {
        MatchDto dto = new MatchDto();
        dto.setId(match.getId());
        dto.setHomeTeamId(match.getHomeTeam().getId());
        dto.setAwayTeamId(match.getAwayTeam().getId());
        dto.setHomeTeamName(match.getHomeTeam().getName());
        dto.setAwayTeamName(match.getAwayTeam().getName());
        dto.setDate(match.getDate());
        dto.setHomeGoals(match.getHomeGoals());
        dto.setAwayGoals(match.getAwayGoals());
        dto.setPlayed(match.isPlayed());
        dto.setPlayerMatchStats(playerMatchStatMapper.toDtoList(match.getPlayerMatchStats()));
        return dto;
    }

    public List<MatchDto> toDtoList(List<Match> matches){
        List<MatchDto> matchesDto = matches.stream().map(this::toDto).toList();
        return matchesDto;
    }

    public UpdateMatchRequest toUpdateMatchRequest(Match match) {
        UpdateMatchRequest request = new UpdateMatchRequest();
        request.setHomeTeamName(match.getHomeTeam().getName());
        request.setHomeGoals(match.getHomeGoals());
        request.setAwayTeamName(match.getAwayTeam().getName());
        request.setAwayGoals(match.getAwayGoals());
        request.setNotes(match.getNotes());
        request.setPlayed(match.isPlayed());
        request.setDate(match.getDate());
        request.setPlayersStats(playerMatchStatMapper.toDtoList(match.getPlayerMatchStats()));
        return request;
    }

    public Match toEntity(RegisterMatchRequest request, Team homeTeam, Team awayTeam, Matchday matchday) {
        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setDate(request.getDate());
        match.setMatchday(matchday);
        return match;
    }

    public Match update(UpdateMatchRequest request, Match match) {
        match.setHomeGoals(request.getHomeGoals());
        match.setAwayGoals(request.getAwayGoals());
        match.setDate(request.getDate());
        match.setPlayed(request.isPlayed());
        match.setNotes(request.getNotes());
        return match;
    }

}
