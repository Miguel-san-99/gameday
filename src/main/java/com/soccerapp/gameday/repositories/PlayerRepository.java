package com.soccerapp.gameday.repositories;

import com.soccerapp.gameday.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
