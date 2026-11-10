package com.soccerapp.gameday.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "player_match_stat")
public class PlayerMatchStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jugador_id")
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partido_id")
    private Match match;
    private Integer goals;
    private Integer yellowCards;
    private boolean redCard;

    public PlayerMatchStat() {
    }

    public PlayerMatchStat(Long id, Player player, Match match, Integer goals, Integer yellowCards, boolean redCard) {
        this.id = id;
        this.player = player;
        this.match = match;
        this.goals = goals;
        this.yellowCards = yellowCards;
        this.redCard = redCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }

    public boolean isRedCard() {
        return redCard;
    }

    public void setRedCard(boolean redCard) {
        this.redCard = redCard;
    }

    @Override
    public String toString() {
        return "PlayerMatchStat{" +
                "id=" + id +
                ", player=" + player.getId() +
                ", match=" + match.getId() +
                ", goals=" + goals +
                ", yellowCards=" + yellowCards +
                ", redCard=" + redCard +
                '}';
    }
}
