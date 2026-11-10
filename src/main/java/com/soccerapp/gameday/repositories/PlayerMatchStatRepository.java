package com.soccerapp.gameday.repositories;

import com.soccerapp.gameday.models.PlayerMatchStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerMatchStatRepository extends JpaRepository<PlayerMatchStat, Long> {
    void deleteByMatchId(Long matchId);
}
