package com.soccerapp.gameday.repositories;

import com.soccerapp.gameday.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long>{
    
}