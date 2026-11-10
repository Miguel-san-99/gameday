package com.soccerapp.gameday.services;

import com.soccerapp.gameday.models.PlayerMatchStat;
import com.soccerapp.gameday.repositories.PlayerMatchStatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlayerMatchStatService {
    private final PlayerMatchStatRepository playerMatchStatRepository;

    public PlayerMatchStatService(PlayerMatchStatRepository playerMatchStatRepository) {
        this.playerMatchStatRepository = playerMatchStatRepository;
    }
    public void deleteByMatchId(Long matchId) {
        playerMatchStatRepository.deleteByMatchId(matchId);
    }

    public PlayerMatchStat savePlayerMatchStat(PlayerMatchStat playerMatchStat) {
        if (playerMatchStat == null) {
            throw new IllegalArgumentException("La estadistica del jugador no puede ser null.");
        }
        return playerMatchStatRepository.save(playerMatchStat);
    }
}
