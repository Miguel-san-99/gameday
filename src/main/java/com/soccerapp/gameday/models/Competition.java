package com.soccerapp.gameday.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Competition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length=50)
    private String name;
    
    @OneToMany(mappedBy = "competition", //Es para vincular a todos los torneos con el id de competencia y poder acceder a ellos a traves de esta lista
            cascade = CascadeType.ALL, //Sirve para si elimino una competencia, tambien se eliminen todos los torneos relacionados, o en caso contrario si guardo una competencia, tambien se guarden todos los torneos relacionados a dicha competencia
            orphanRemoval = true, //Sirve para si en caso elimino un torneo de la lista, tambien se elimine el torneo de la base de datos y no solo de mi lista en competencia
            fetch = FetchType.EAGER)
    private List<Tournament> tournaments = new ArrayList<>();

    public Competition() {
    }

    public Competition(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
