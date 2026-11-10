package com.soccerapp.gameday.controllers;

import com.soccerapp.gameday.dtos.CompetitionDto;
import com.soccerapp.gameday.mappers.CompetitionMapper;
import com.soccerapp.gameday.models.Competition;
import com.soccerapp.gameday.services.CompetitionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("${admin.prefix}/competition")
public class CompetitionController {
    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService, CompetitionMapper competitionMapper) {
        this.competitionService = competitionService;
    }

    @PostMapping("/register")
    public String registerCompetition(@ModelAttribute("competitionForm") CompetitionDto request, RedirectAttributes redirectAttributes) {
        try {
            competitionService.saveCompetition(request);
            redirectAttributes.addFlashAttribute("message", "Se ha registrado exitosamente la competencia");
            return "redirect:/admin/tournament";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/tournament";
        }
    }

}