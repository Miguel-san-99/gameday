package com.soccerapp.gameday.controllers;

import com.soccerapp.gameday.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {
    
    @Autowired
    private CompetitionService competitionService;
    
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("competitions", competitionService.getAllCompetitions());
    }
}
