package com.soccerapp.gameday.repositories;

import com.soccerapp.gameday.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long>{
    
}
