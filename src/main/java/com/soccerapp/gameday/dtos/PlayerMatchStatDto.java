package com.soccerapp.gameday.dtos;

public class PlayerMatchStatDto {
    private Long playerId;
    private String name;
    private Integer goals;
    private Integer yellowCards;
    private boolean redCard;

    public PlayerMatchStatDto() {
    }

    public PlayerMatchStatDto(Long playerId, String name, Integer goals, Integer yellowCards, boolean redCard) {
        this.playerId = playerId;
        this.name = name;
        this.goals = goals;
        this.yellowCards = yellowCards;
        this.redCard = redCard;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlayerMatchStatDto{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", goals=" + goals +
                ", yellowCards=" + yellowCards +
                ", redCard=" + redCard +
                '}';
    }
}
