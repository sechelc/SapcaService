package com.rogames.engine.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sechelc on 10.04.2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "games")
public class GameDetails {
    private Long id;
    private List<Team> teams;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAME_ID", unique = true, length = 20)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    @OneToMany(mappedBy = "gameDetails", cascade = CascadeType.ALL)
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
