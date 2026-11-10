package com.soccerapp.gameday.services;

import com.soccerapp.gameday.dtos.TeamStatsRequest;
import com.soccerapp.gameday.dtos.TournamentDto;
import com.soccerapp.gameday.enums.TeamStatus;
import com.soccerapp.gameday.mappers.TournamentMapper;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.models.Match;
import com.soccerapp.gameday.models.Tournament;
import com.soccerapp.gameday.repositories.TournamentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    public TournamentService(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    @Transactional(readOnly = true)
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe el torneo con id: " + id));
    }

    @Transactional(readOnly = true)
    public TournamentDto getTournamentDtoById(Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe el torneo con id: " + id));
        return tournamentMapper.toDto(tournament);
    }
    
    @Transactional
    public Tournament saveTournament(Tournament tournament) {
        if (tournament == null) {
            throw new IllegalArgumentException("El torneo no puede ser null.");
        }
        return tournamentRepository.save(tournament);
    }
    
    
    //To do: optimizar tabla recorriendo partidos y verificando equipos en lugar de recorrer equipos
    public List<TeamStatsRequest> getLeaderboard(Long tournamentId) {
        Tournament tournament = this.getTournamentById(tournamentId);
        List<Team> teams = tournament.getTeams();
        List<TeamStatsRequest> teamStandings = new ArrayList<>();
        for (Team team : teams) {
            if(team.getStatus() == TeamStatus.ACTIVE) {
                TeamStatsRequest teamStatsAux = new TeamStatsRequest();
                teamStatsAux.setName(team.getName());
                List<Match> homeMatches = team.getHomeMatches();
                List<Match> awayMatches = team.getAwayMatches();
                teamStatsAux.setPj(homeMatches.size() + awayMatches.size());

                for (Match match : homeMatches) {
                    if (match.isPlayed()) {
                        if (match.getHomeGoals() > match.getAwayGoals()) {
                            teamStatsAux.setPg(teamStatsAux.getPg() + 1);
                            teamStatsAux.setPts(teamStatsAux.getPts() + 3);
                        } else if (match.getHomeGoals() < match.getAwayGoals()) {
                            teamStatsAux.setPp(teamStatsAux.getPp() + 1);
                        } else {
                            teamStatsAux.setPe(teamStatsAux.getPe() + 1);
                            teamStatsAux.setPts(teamStatsAux.getPts() + 1);
                        }
                        teamStatsAux.setGf(teamStatsAux.getGf() + match.getHomeGoals());
                        teamStatsAux.setGc(teamStatsAux.getGc() + match.getAwayGoals());
                    } else {
                        teamStatsAux.setPj(teamStatsAux.getPj() - 1);
                    }
                }

                for (Match match : awayMatches) {
                    if (match.isPlayed()) {
                        if (match.getHomeGoals() < match.getAwayGoals()) {
                            teamStatsAux.setPg(teamStatsAux.getPg() + 1);
                            teamStatsAux.setPts(teamStatsAux.getPts() + 3);
                        } else if (match.getHomeGoals() > match.getAwayGoals()) {
                            teamStatsAux.setPp(teamStatsAux.getPp() + 1);
                        } else {
                            teamStatsAux.setPe(teamStatsAux.getPe() + 1);
                            teamStatsAux.setPts(teamStatsAux.getPts() + 1);
                        }
                        teamStatsAux.setGf(teamStatsAux.getGf() + match.getAwayGoals());
                        teamStatsAux.setGc(teamStatsAux.getGc() + match.getHomeGoals());
                    } else {
                        teamStatsAux.setPj(teamStatsAux.getPj() - 1);
                    }
                }
                teamStatsAux.setDg(teamStatsAux.getGf() - teamStatsAux.getGc());
                teamStandings.add(teamStatsAux);
            }
        }
        teamStandings.sort(
                Comparator.comparing(TeamStatsRequest::getPts, Comparator.reverseOrder())
                        .thenComparing(TeamStatsRequest::getDg, Comparator.reverseOrder())
                        .thenComparing(TeamStatsRequest::getGf, Comparator.reverseOrder())
        );
        return teamStandings;
    }

    public List<TeamStatsRequest> getWinList(List<TeamStatsRequest> teamStandings) {
        List<TeamStatsRequest> teamStandingsCopy = new ArrayList<>(teamStandings);
        teamStandingsCopy.sort(Comparator.comparing(TeamStatsRequest::getPg).reversed());
        if (teamStandingsCopy.size() > 5) {
            return teamStandingsCopy.subList(0, 5);
        }
        return teamStandingsCopy;
    }

    public List<TeamStatsRequest> getDrawList(List<TeamStatsRequest> teamStandings) {
        List<TeamStatsRequest> teamStandingsCopy = new ArrayList<>(teamStandings);
        teamStandingsCopy.sort(Comparator.comparing(TeamStatsRequest::getPe).reversed());
        if (teamStandingsCopy.size() > 5) {
            return teamStandingsCopy.subList(0, 5);
        }
        return teamStandingsCopy;
    }

    public List<TeamStatsRequest> getDefeatList(List<TeamStatsRequest> teamStandings) {
        List<TeamStatsRequest> teamStandingsCopy = new ArrayList<>(teamStandings);
        teamStandingsCopy.sort(Comparator.comparing(TeamStatsRequest::getPp).reversed());
        if (teamStandingsCopy.size() > 5) {
            return teamStandingsCopy.subList(0, 5);
        }
        return teamStandingsCopy;
    }

    public List<TeamStatsRequest> getGoalsScored(List<TeamStatsRequest> teamStandings) {
        List<TeamStatsRequest> teamStandingsCopy = new ArrayList<>(teamStandings);
        teamStandingsCopy.sort(Comparator.comparing(TeamStatsRequest::getGf).reversed());
        if (teamStandingsCopy.size() > 5) {
            return teamStandingsCopy.subList(0, 5);
        }
        return teamStandingsCopy;
    }

    public List<TeamStatsRequest> getGoalsReceived(List<TeamStatsRequest> teamStandings) {
        List<TeamStatsRequest> teamStandingsCopy = new ArrayList<>(teamStandings);
        teamStandingsCopy.sort(Comparator.comparing(TeamStatsRequest::getGc).reversed());
        if (teamStandingsCopy.size() > 5) {
            return teamStandingsCopy.subList(0, 5);
        }
        return teamStandingsCopy;
    }
}
