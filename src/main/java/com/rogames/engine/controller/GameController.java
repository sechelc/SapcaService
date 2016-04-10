package com.rogames.engine.controller;

import com.rogames.engine.GameEngineService;
import com.rogames.engine.entity.GameDetails;
import com.rogames.engine.entity.Player;
import com.rogames.engine.entity.Round;
import com.rogames.engine.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sechelc on 10.04.2016.
 */
@Controller
public class GameController {
    @Autowired
    private GameEngineService gameEngineService;

    @RequestMapping("/startGame")
    @ResponseBody
    public String createGame(HttpServletRequest request, HttpSession session){

        session.invalidate();
        HttpSession newSession = request.getSession(); // create session

        GameDetails gameDetails = new GameDetails();
        ArrayList<Team> teams = new ArrayList<>();
        Team team1 = createTeam(gameDetails, "Cristi", "Cristina");
        Team team2 = createTeam(gameDetails, "Mihai", "Mihaela");
        teams.add(team1);
        teams.add(team2);
        gameDetails.setTeams(teams);
        return gameEngineService.startGame(gameDetails);
    }

    @RequestMapping(value = "/game/{gameId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GameDetails getGameDetails(@PathVariable String gameId){
        GameDetails gameDetails = gameEngineService.retrieveGameDetails(Long.parseLong(gameId));
        List<Team> teams = gameDetails.getTeams();
        return gameDetails;
    }

    @RequestMapping(value = "/nextRound",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Round getNextRound(){
        return gameEngineService.getNextRound();
    }

    private Team createTeam(GameDetails gameDetails, String... players){
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
