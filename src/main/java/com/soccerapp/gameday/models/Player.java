package com.soccerapp.gameday.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id", nullable = false)
    private Team team;
    
    private LocalDate birthDate;
    
    @OneToMany(mappedBy = "player")
    private List<PlayerMatchStat> playerMatchStats = new ArrayList<>();

    public Player() {
    }

    public Player(Long id, String name, Team team, LocalDate birthDate, List<PlayerMatchStat> playerMatchStats) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.birthDate = birthDate;
        this.playerMatchStats = playerMatchStats;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<PlayerMatchStat> getPlayerMatchStats() {
        return playerMatchStats;
    }

    public void setPlayerMatchStats(List<PlayerMatchStat> playerMatchStats) {
        this.playerMatchStats = playerMatchStats;
    }
}
