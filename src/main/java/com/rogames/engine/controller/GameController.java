package com.rogames.engine.controller;

import com.rogames.engine.GameEngineService;
import com.rogames.engine.entity.GameDetails;
import com.rogames.engine.entity.Round;
import com.rogames.engine.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Controller("session")
public class GameController {

    @Autowired
    private GameEngineService gameEngineService;

    @RequestMapping("/startGame")
    @ResponseBody
    public String createGame(@RequestParam Integer noOfTeams){
        return gameEngineService.startGame(noOfTeams);
    }

    @RequestMapping(value = "/game/{gameId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GameDetails getGameDetails(@PathVariable String gameId){
        return gameEngineService.retrieveGameDetails(Long.parseLong(gameId));
    }

    @RequestMapping(value = "/nextRound/{gameId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Round getNextRound(@PathVariable String gameId){
        return gameEngineService.getNextRound(gameId);
    }

    @RequestMapping(value = "/addPoints/{teamId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean getNextRound( @PathVariable long teamId, @RequestParam int points){
        return gameEngineService.addPoints(teamId, points);
    }

}
