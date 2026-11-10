package com.soccerapp.gameday.repositories;

import com.soccerapp.gameday.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>{
    
}
