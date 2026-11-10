package com.soccerapp.gameday.repositories;

import com.soccerapp.gameday.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{
    
}
