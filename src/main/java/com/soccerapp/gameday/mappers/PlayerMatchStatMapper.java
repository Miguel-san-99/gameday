package com.soccerapp.gameday.mappers;

import com.soccerapp.gameday.dtos.PlayerMatchStatDto;
import com.soccerapp.gameday.models.Match;
import com.soccerapp.gameday.models.Player;
import com.soccerapp.gameday.models.PlayerMatchStat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerMatchStatMapper {

    public List<PlayerMatchStatDto> toDtoList(List<PlayerMatchStat> playerMatchStats){
        List<PlayerMatchStatDto> dtos = new ArrayList<>();
        for (PlayerMatchStat playerStat : playerMatchStats) {
            PlayerMatchStatDto playerStatAux = new PlayerMatchStatDto();
            playerStatAux.setPlayerId(playerStat.getPlayer().getId());
            playerStatAux.setName(playerStat.getPlayer().getName());
            playerStatAux.setGoals(playerStat.getGoals());
            playerStatAux.setYellowCards(playerStat.getYellowCards());
            playerStatAux.setRedCard(playerStat.isRedCard());
            dtos.add(playerStatAux);
        }
        return dtos;
    }

    public PlayerMatchStat toEntity(PlayerMatchStatDto playerStatDto, Match match, Player player) {
        PlayerMatchStat playerMatchStat = new PlayerMatchStat();
        playerMatchStat.setPlayer(player);
        playerMatchStat.setMatch(match);
        playerMatchStat.setGoals(playerStatDto.getGoals());
        playerMatchStat.setYellowCards(playerStatDto.getYellowCards());
        playerMatchStat.setRedCard(playerStatDto.isRedCard());
        return  playerMatchStat;
    }
}
