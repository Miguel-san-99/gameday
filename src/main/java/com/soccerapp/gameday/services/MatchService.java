package com.soccerapp.gameday.services;

import com.soccerapp.gameday.dtos.MatchDto;
import com.soccerapp.gameday.dtos.PlayerMatchStatDto;
import com.soccerapp.gameday.dtos.RegisterMatchRequest;
import com.soccerapp.gameday.dtos.UpdateMatchRequest;
import com.soccerapp.gameday.mappers.MatchMapper;
import com.soccerapp.gameday.mappers.PlayerMatchStatMapper;
import com.soccerapp.gameday.models.Match;
import com.soccerapp.gameday.models.Matchday;
import com.soccerapp.gameday.models.PlayerMatchStat;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.repositories.MatchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchService {
    
    private final MatchRepository matchRepository;
    private final MatchdayService matchdayService;
    private final MatchMapper matchMapper;
    private final PlayerMatchStatMapper playerMatchStatMapper;
    private final TeamService teamService;
    private final PlayerService playerService;

    public MatchService(MatchRepository matchRepository, MatchdayService matchdayService, MatchMapper matchMapper, PlayerMatchStatService playerMatchStatService, PlayerMatchStatMapper playerMatchStatMapper, TeamService teamService, PlayerService playerService) {
        this.matchRepository = matchRepository;
        this.matchdayService = matchdayService;
        this.matchMapper = matchMapper;
        this.playerMatchStatMapper = playerMatchStatMapper;
        this.teamService = teamService;
        this.playerService = playerService;
    }
    
    @Transactional(readOnly = true)
    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe el partido con id: " + id));
    }

    @Transactional(readOnly = true)
    public MatchDto getMatchDtoById(Long id) {
        Match match = matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe el partido con id: " + id));
        return matchMapper.toDto(match);
    }
    
    @Transactional
    public MatchDto saveMatch(RegisterMatchRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El partido no puede ser null.");
        }
        Team homeTeam = teamService.getTeamById(request.getHomeTeamId());
        Team awayTeam = teamService.getTeamById(request.getAwayTeamId());
        Matchday matchday = matchdayService.getMatchdayById(request.getMatchdayId());
        Match match = matchMapper.toEntity(request, homeTeam, awayTeam, matchday);
        return matchMapper.toDto(matchRepository.save(match));
    }
    
    @Transactional
    public void deleteMatch(Long id) {
        Match match = getMatchById(id);
        Matchday matchday = match.getMatchday();
        matchday.getMatches().remove(match);
        matchRepository.delete(match);
    }

    @Transactional
    public MatchDto updateMatch(Long matchId, UpdateMatchRequest matchRequest) {

        Match match = matchMapper.update(matchRequest, this.getMatchById(matchId));
        match.removeAllPlayerMatchStats();
        if (matchRequest.getPlayersStats() != null) {
            for (PlayerMatchStatDto playerMatchStatDto : matchRequest.getPlayersStats()) {
                PlayerMatchStat playerMatchStat = playerMatchStatMapper.toEntity(playerMatchStatDto, match, playerService.getPlayerById(playerMatchStatDto.getPlayerId()));
                match.addPlayerMatchStat(playerMatchStat);
            }
        }
        return matchMapper.toDto(matchRepository.save(match));
    }

    public UpdateMatchRequest setUpdateRequest(Long matchId) {
        Match match = this.getMatchById(matchId);
        return matchMapper.toUpdateMatchRequest(match);
    }
}
