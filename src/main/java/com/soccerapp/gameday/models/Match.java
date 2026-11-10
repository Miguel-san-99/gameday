package com.soccerapp.gameday.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_local_id")
    private Team homeTeam;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_visita_id")
    private Team awayTeam;
    
    private Integer homeGoals = 0;
    private Integer awayGoals = 0;
    private LocalTime date;
    private boolean played = false;
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jornada_id", nullable = false)
    private Matchday matchday;
    
    @OneToMany(mappedBy = "match",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PlayerMatchStat> playerMatchStats = new ArrayList<>();

    public Match() {
    }

    public Match(List<PlayerMatchStat> playerMatchStats, Matchday matchday, String notes, boolean played, LocalTime Date, Integer awayGoals, Integer homeGoals, Team awayTeam, Team homeTeam, Long id) {
        this.playerMatchStats = playerMatchStats;
        this.matchday = matchday;
        this.notes = notes;
        this.played = played;
        this.date = Date;
        this.awayGoals = awayGoals;
        this.homeGoals = homeGoals;
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
        this.id = id;
    }

    public void addPlayerMatchStat(PlayerMatchStat stat) {
        playerMatchStats.add(stat);
        stat.setMatch(this);
    }

    public void removeAllPlayerMatchStats() {
        playerMatchStats.clear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Matchday getMatchday() {
        return matchday;
    }

    public void setMatchday(Matchday matchday) {
        this.matchday = matchday;
    }

    public List<PlayerMatchStat> getPlayerMatchStats() {
        return playerMatchStats;
    }

    public void setPlayerMatchStats(List<PlayerMatchStat> playerMatchStats) {
        this.playerMatchStats = playerMatchStats;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchday=" + matchday.getName() +
                ", notes='" + notes + '\'' +
                ", played=" + played +
                ", date=" + date +
                ", awayGoals=" + awayGoals +
                ", homeGoals=" + homeGoals +
                ", awayTeam=" + awayTeam +
                ", homeTeam=" + homeTeam +
                ", id=" + id +
                '}';
    }
}
