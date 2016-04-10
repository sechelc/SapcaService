package com.rogames.engine.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sechelc on 10.04.2016.
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID",unique = true, length = 20)
    private Long id;

    @ManyToOne
    @JoinColumn(name="GAME_ID")
    private GameDetails gameDetails;

    @Column
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameDetails getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(GameDetails gameDetails) {
        this.gameDetails = gameDetails;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
