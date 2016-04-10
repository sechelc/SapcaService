package com.rogames.engine;

import com.rogames.account.Account;
import com.rogames.engine.entity.GameDetails;
import com.rogames.engine.entity.GameStats;
import com.rogames.engine.entity.Round;
import com.rogames.engine.entity.Team;
import com.rogames.engine.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Id;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by sechelc on 10.04.2016.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GameEngineService {

    @Autowired
    private GameRepository gameRepository;

    private Stack<Round> rounds = new Stack<>();
    private List<Long> teamIds;
    private int nextTeamId;

    @PostConstruct
    public void init() {
        gameRepository.createRound(new Round("nu da cioara de pe gard pe lalala", "3", "anything"));
        gameRepository.createRound(new Round("alta intrebare", "5", "deseneaza, mimeaza, vorbeste"));
        gameRepository.createRound(new Round("masa", "1", "mima"));

        List<Round> roundList = gameRepository.retrieveAllRounds();
        long seed = System.nanoTime();
        Collections.shuffle(roundList, new Random(seed));
        rounds.addAll(roundList);
    }

    public String startGame(GameDetails gameDetails) {
        GameDetails game = gameRepository.createGame(gameDetails);
        teamIds = game.getTeams().stream().map(Team::getId).collect(Collectors.toList());
        return game.getId().toString();
    }

    public GameStats getGameStats(String gameId) {
        return null;
    }

    public GameDetails retrieveGameDetails(Long gameId) {
        return gameRepository.retrieveGameDetails(gameId);
    }


    public Round getNextRound() {
        Round pop = rounds.pop();
        pop.setTeam(gameRepository.findTeam(teamIds.get(nextTeamId%teamIds.size())));
        return pop;
    }
}
