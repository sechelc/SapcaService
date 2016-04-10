package com.rogames.engine.controller;

import com.rogames.engine.GameEngineService;
import com.rogames.engine.model.GameDetails;
import com.rogames.engine.model.Player;
import com.rogames.engine.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by sechelc on 10.04.2016.
 */
@Controller
public class GameController {

    @Autowired
    private GameEngineService gameEngineService;

    @RequestMapping(name = "/startGame")
    @ResponseBody
    public String createGame(){
        GameDetails gameDetails = new GameDetails();
        ArrayList<Team> teams = new ArrayList<>();
        Team team1 = createTeam(gameDetails, "Cristi", "Cristina");
        Team team2 = createTeam(gameDetails, "Mihai", "Mihaela");
        teams.add(team1);
        teams.add(team2);
        gameDetails.setTeams(teams);
        return gameEngineService.startGame(gameDetails);
    }

    public Team createTeam(GameDetails gameDetails, String... players){
        Team team = new Team();
        team.setGameDetails(gameDetails);
        ArrayList<Player> ps = new ArrayList<>();
        team.setPlayers(ps);
        for (String playerName : players) {
            Player p = new Player();
            p.setName(playerName);
            p.setTeam(team);
           ps.add(p);
        }
        return team;
    }
}
