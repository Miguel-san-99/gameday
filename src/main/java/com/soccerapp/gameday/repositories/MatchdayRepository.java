package com.soccerapp.gameday.repositories;

import com.soccerapp.gameday.models.Matchday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchdayRepository extends JpaRepository<Matchday, Long>{
    
}
