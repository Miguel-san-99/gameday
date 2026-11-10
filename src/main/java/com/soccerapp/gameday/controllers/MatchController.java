package com.soccerapp.gameday.controllers;


import com.soccerapp.gameday.dtos.MatchDto;
import com.soccerapp.gameday.dtos.RegisterMatchRequest;
import com.soccerapp.gameday.dtos.UpdateMatchRequest;
import com.soccerapp.gameday.mappers.MatchMapper;
import com.soccerapp.gameday.mappers.PlayerMapper;
import com.soccerapp.gameday.models.Match;
import com.soccerapp.gameday.models.Matchday;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.services.MatchService;
import com.soccerapp.gameday.services.TeamService;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("${admin.prefix}/match")
public class MatchController {
    private final MatchService matchService;
    private final TeamService teamService;

    public MatchController(MatchService matchService, MatchMapper matchMapper, PlayerMapper playerMapper, TeamService teamService) {
        this.matchService = matchService;
        this.teamService = teamService;
    }

    @GetMapping("/{matchId}")
    public String adminMatch(@PathVariable Long matchId, Model model) {
        Match match = matchService.getMatchById(matchId);
        UpdateMatchRequest matchForm = matchService.setUpdateRequest(matchId);
        model.addAttribute("matchId", matchId);
        model.addAttribute("matchForm", matchForm);
        model.addAttribute("homePlayers", teamService.getTeamDtoById(match.getHomeTeam().getId()).getPlayers());
        model.addAttribute("awayPlayers", teamService.getTeamDtoById(match.getAwayTeam().getId()).getPlayers());
        model.addAttribute("tournamentId", match.getMatchday().getTournament().getId());
        model.addAttribute("matchdayId", match.getMatchday().getId());
        return "manage/admin_match";
    }

    @PostMapping("/register-match")
    public String registerMatch(@ModelAttribute("matchForm") RegisterMatchRequest matchRequest, RedirectAttributes redirectAttributes) {
        try {
            matchService.saveMatch(matchRequest);
            redirectAttributes.addFlashAttribute("message", "Se ha registrado exitosamente el partido");
            return "redirect:/admin/matchday/" + matchRequest.getMatchdayId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/matchday/" + matchRequest.getMatchdayId();
        }
    }

    @PostMapping("/{matchId}/update-match")
    public String updateMatch(@PathVariable Long matchId, @ModelAttribute("matchForm") UpdateMatchRequest matchRequest, RedirectAttributes redirectAttributes) {
        try {
            matchService.updateMatch(matchId, matchRequest);
            redirectAttributes.addFlashAttribute("message", "Se han registrado exitosamente los resultados");
            return "redirect:/admin/match/" + matchId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/match/" + matchId;
        }
    }

    @PostMapping("/{matchId}/delete-match")
    public String deleteMatch(@RequestParam Long matchdayId, @PathVariable Long matchId, RedirectAttributes redirectAttributes) {
        try {
            matchService.deleteMatch(matchId);
            redirectAttributes.addFlashAttribute("message", "Se ha eliminado exitosamente el partido");
            return "redirect:/admin/matchday/" + matchdayId;
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/matchday/" + matchdayId;
        }
    }
}
