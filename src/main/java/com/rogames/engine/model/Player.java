package com.rogames.engine.model;

import javax.persistence.*;

/**
 * Created by sechelc on 10.04.2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID",unique = true, length = 20)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
