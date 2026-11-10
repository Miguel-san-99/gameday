package com.soccerapp.gameday.controllers;

import com.soccerapp.gameday.dtos.*;
import com.soccerapp.gameday.mappers.UserMapper;
import com.soccerapp.gameday.models.*;
import com.soccerapp.gameday.services.CompetitionService;
import com.soccerapp.gameday.services.MatchdayService;
import com.soccerapp.gameday.services.TournamentService;
import com.soccerapp.gameday.services.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    
    private final CompetitionService competitionService;
    
    private final TournamentService tournamentService;
    
    private final UserService userService;
    
    private final MatchdayService matchdayService;

    public MainController(CompetitionService competitionService, TournamentService tournamentService, UserService userService, MatchdayService matchdayService, UserMapper userMapper) {
        this.competitionService = competitionService;
        this.tournamentService = tournamentService;
        this.userService = userService;
        this.matchdayService = matchdayService;
    }

    @GetMapping("/index")
    public String index() {
        return "core/index";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "core/about_us";
    }

    @GetMapping("/tournament/{tournamentId}")
    public String tournamentDetails(@PathVariable Long tournamentId, Model model) {

        TournamentDto tournament = tournamentService.getTournamentDtoById(tournamentId);
        CompetitionDto competition = competitionService.getCompetitionDtoById(tournament.getCompetitionId());
        List<TeamStatsRequest> leaderboard = tournamentService.getLeaderboard(tournamentId);
        List<TeamStatsRequest> winList = tournamentService.getWinList(leaderboard);
        List<TeamStatsRequest> drawList = tournamentService.getDrawList(leaderboard);
        List<TeamStatsRequest> defeatList = tournamentService.getDefeatList(leaderboard);
        List<TeamStatsRequest> goalsScored = tournamentService.getGoalsScored(leaderboard);
        List<TeamStatsRequest> goalsReceived = tournamentService.getGoalsReceived(leaderboard);

        model.addAttribute("tournament", tournament);
        model.addAttribute("tournaments", competition.getTournaments());
        model.addAttribute("matchdays", tournament.getMatchdays());
        model.addAttribute("matchday", new Matchday());
        model.addAttribute("leaderboard", leaderboard);
        model.addAttribute("winList", winList);
        model.addAttribute("drawList", drawList);
        model.addAttribute("defeatList", defeatList);
        model.addAttribute("goalsScored", goalsScored);
        model.addAttribute("goalsRecived", goalsReceived);

        return "core/competition";
    }

    @GetMapping("/login")
    public String login() {
        return "manage/login";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("userForm", new RegisterUserRequest());
        return "core/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("userForm") RegisterUserRequest request, RedirectAttributes redirectAttributes) {
        try {
            userService.saveUserAdmin(request);
            redirectAttributes.addFlashAttribute("message", "Se ha registrado exitosamente el usuario");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/matches/{matchdayId}")
    public String getMatches(@PathVariable Long matchdayId, Model model) {
        MatchdayDto matchday = matchdayService.getMatchdayDtoById(matchdayId);
        model.addAttribute("matchday", matchday);
        return "fragments/matches :: matches";
    }
}
