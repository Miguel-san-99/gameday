package com.soccerapp.gameday.controllers;


import com.soccerapp.gameday.dtos.CompetitionDto;
import com.soccerapp.gameday.dtos.RegisterMatchdayRequest;
import com.soccerapp.gameday.dtos.RegisterTeamRequest;
import com.soccerapp.gameday.dtos.RegisterTournamentRequest;
import com.soccerapp.gameday.models.Competition;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.models.Tournament;
import com.soccerapp.gameday.services.CompetitionService;
import com.soccerapp.gameday.services.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("${admin.prefix}/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    private final CompetitionService competitionService;

    public TournamentController(TournamentService tournamentService, CompetitionService competitionService) {
        this.tournamentService = tournamentService;
        this.competitionService = competitionService;
    }

    @GetMapping
    public String tournamentSelection(Model model) {
        model.addAttribute("competitionForm", new CompetitionDto());
        model.addAttribute("tournamentForm", new RegisterTournamentRequest());
        return "manage/tournament_selection";
    }

    @GetMapping("/{tournamentId}")
    public String adminTournament(@PathVariable Long tournamentId, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        RegisterTeamRequest teamForm = new RegisterTeamRequest();
        teamForm.setTournamentId(tournamentId);
        RegisterMatchdayRequest matchdayForm = new RegisterMatchdayRequest();
        matchdayForm.setTournamentId(tournamentId);
        Competition competition = tournament.getCompetition();
        List<Team> teams = tournament.getTeams();
        model.addAttribute("competition", competition);
        model.addAttribute("tournament", tournament);
        model.addAttribute("teams", teams);
        model.addAttribute("teamForm", teamForm);
        model.addAttribute("matchdays", tournament.getMatchdays());
        model.addAttribute("matchdayForm", matchdayForm);
        model.addAttribute("nextMatchday", "Jornada " + (tournament.getMatchdays().size() + 1));
        return "manage/admin_tournament";
    }

    @GetMapping("/tournaments")
    public String getTournaments(@RequestParam Long competitionId, Model model) {

        Competition competition = competitionService.getCompetitionById(competitionId);

        model.addAttribute("tournaments", competition.getTournaments());

        return "fragments/select_tournament :: selectTournament";
    }

    @PostMapping("/register")
    public String registerTournament(@ModelAttribute("tournamentForm") RegisterTournamentRequest request, RedirectAttributes redirectAttributes) {
        try {
            Tournament tournament = new Tournament();
            tournament.setName(request.getName());
            tournament.setCompetition(competitionService.getCompetitionById(request.getCompetitionId()));
            tournamentService.saveTournament(tournament);
            redirectAttributes.addFlashAttribute("message", "Se ha registrado exitosamente el torneo");
            return "redirect:/admin/tournament";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/tournament";
        }
    }

}
