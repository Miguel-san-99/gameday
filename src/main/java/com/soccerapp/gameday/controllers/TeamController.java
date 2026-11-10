package com.soccerapp.gameday.controllers;


import com.soccerapp.gameday.dtos.*;
import com.soccerapp.gameday.enums.Role;
import com.soccerapp.gameday.enums.TeamStatus;
import com.soccerapp.gameday.mappers.PlayerMapper;
import com.soccerapp.gameday.mappers.TeamMapper;
import com.soccerapp.gameday.models.Player;
import com.soccerapp.gameday.models.Team;
import com.soccerapp.gameday.models.User;
import com.soccerapp.gameday.services.PlayerService;
import com.soccerapp.gameday.services.TeamService;
import com.soccerapp.gameday.services.TournamentService;
import com.soccerapp.gameday.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("${admin.prefix}/team")
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;
    private final PlayerService playerService;
    private final TournamentService tournamentService;
    private final UserService userService;
    private final PlayerMapper playerMapper;


    public TeamController(TeamService teamService, TeamMapper teamMapper, PlayerService playerService, TournamentService tournamentService, UserService userService, PlayerMapper playerMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
        this.playerService = playerService;
        this.tournamentService = tournamentService;
        this.userService = userService;
        this.playerMapper = playerMapper;
    }

    @GetMapping("/{teamId}")
    public String adminTeam(@PathVariable Long teamId, Model model) {
        TeamDto teamDto = teamService.getTeamDtoById(teamId);
        model.addAttribute("teamId", teamId);
        model.addAttribute("playerForm", new RegisterPlayerRequest());
        model.addAttribute("teamForm", teamService.setUpdateRequest(teamId));
        model.addAttribute("players", teamDto.getPlayers());
        model.addAttribute("tournamentId", teamDto.getTournamentId());
        return "manage/admin_team";
    }

    @PostMapping("/{teamId}/register-player")
    public String registerPlayer(@PathVariable Long teamId, @ModelAttribute("playerForm") RegisterPlayerRequest request, RedirectAttributes redirectAttributes) {
        try {
            request.setTeamId(teamId);
            playerService.savePlayer(request);
            redirectAttributes.addFlashAttribute("message", "Se ha registrado exitosamente el jugador");
            return "redirect:/admin/team/" + request.getTeamId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/team/" + request.getTeamId();
        }
    }

    @PostMapping("/register-team")
    public String registerTeam(@ModelAttribute("teamForm") RegisterTeamRequest request, RedirectAttributes redirectAttributes) {
        try {
            teamService.saveTeam(request);
            redirectAttributes.addFlashAttribute("message", "Se ha registrado exitosamente el equipo");
            return "redirect:/admin/tournament/" + request.getTournamentId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/tournament/" + request.getTournamentId();
        }
    }

    @PostMapping("/{teamId}/update-team")
    public String UpdateTeam(@PathVariable Long teamId, @ModelAttribute("teamForm") UpdateTeamRequest request, RedirectAttributes redirectAttributes) {
        try {
            teamService.updateTeam(teamId, request);
            redirectAttributes.addFlashAttribute("message", "El equipo se ha actualizado exitosamente");
            return "redirect:/admin/team/" + teamId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/team/" + teamId;
        }
    }
}
