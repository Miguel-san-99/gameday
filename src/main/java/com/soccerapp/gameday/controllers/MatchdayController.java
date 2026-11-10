package com.soccerapp.gameday.controllers;


import com.soccerapp.gameday.dtos.*;
import com.soccerapp.gameday.mappers.MatchMapper;
import com.soccerapp.gameday.mappers.MatchdayMapper;
import com.soccerapp.gameday.models.Matchday;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.models.Tournament;
import com.soccerapp.gameday.services.MatchdayService;
import com.soccerapp.gameday.services.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("${admin.prefix}/matchday")
public class MatchdayController {
    private final MatchdayService matchdayService;
    private final TournamentService tournamentService;

    public MatchdayController(MatchdayService matchdayService, TournamentService tournamentService) {
        this.matchdayService = matchdayService;
        this.tournamentService = tournamentService;
    }

    @GetMapping("/{matchdayId}")
    public String adminMatchday(@PathVariable Long matchdayId, Model model) {
        MatchdayDto matchdayDto = matchdayService.getMatchdayDtoById(matchdayId);
        Long tournamentId = matchdayDto.getTournamentId();
        RegisterMatchRequest matchForm = new RegisterMatchRequest();
        matchForm.setMatchdayId(matchdayId);
        List<TeamDto> teamsDto = tournamentService.getTournamentDtoById(tournamentId).getTeams();
        List<MatchDto> matchesDto = matchdayDto.getMatches();

        model.addAttribute("tournamentId", tournamentId);
        model.addAttribute("matchday", matchdayDto);
        model.addAttribute("matches", matchesDto);
        model.addAttribute("teams", teamsDto);
        model.addAttribute("matchForm", matchForm);
        return "manage/admin_matchday";
    }

    @PostMapping("/register-matchday")
    public String registerMatchday(@ModelAttribute("matchdayForm") RegisterMatchdayRequest request, RedirectAttributes redirectAttributes) {
        try {
            matchdayService.saveMatchday(request);
            redirectAttributes.addFlashAttribute("message", "Se ha registrado exitosamente la jornada");
            return "redirect:/admin/tournament/" + request.getTournamentId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/tournament/" + request.getTournamentId();
        }
    }
}
