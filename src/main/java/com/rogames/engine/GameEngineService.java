package com.rogames.engine;

import com.rogames.engine.entity.GameDetails;
import com.rogames.engine.entity.Round;
import com.rogames.engine.entity.Team;
import com.rogames.engine.model.Collors;
import com.rogames.engine.model.RoundActions;
import com.rogames.engine.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class GameEngineService {

    public static final long WINNING_POINTS = 25;
    @Autowired
    private GameRepository gameRepository;

    private Stack<Round> rounds = new Stack<>();
    private Map<String, Stack<Round>> roundByGame = new ConcurrentHashMap<>();
    private Map<String, List<Long>> teamIds = new ConcurrentHashMap<>();
    private Map<String, Integer> nextTeamId = new ConcurrentHashMap<>();
    private List<Collors> collors = new ArrayList<>();

    @PostConstruct
    public void init() {
        collors = Arrays.asList(Collors.values());
        updateRoundList();
    }

    void updateRoundList() {
        List<Round> roundList = gameRepository.retrieveAllRounds();
        long seed = System.nanoTime();
        Collections.shuffle(roundList, new Random(seed));
        rounds.addAll(roundList);
    }

    public String startGame(GameDetails gameDetails) {
        GameDetails game = gameRepository.createGame(gameDetails);
        teamIds.put(game.getId().toString(), game.getTeams().stream().map(Team::getId).collect(Collectors.toList()));
        Stack<Round> r = new Stack<>();
        r.addAll(rounds);
        roundByGame.put(game.getId().toString(), r);
        nextTeamId.put(game.getId().toString(), 0);
        return game.getId().toString();
    }

    public GameDetails retrieveGameDetails(Long gameId) {
        return gameRepository.retrieveGameDetails(gameId);
    }


    public Round getNextRound(String gameId) {
        Round pop = roundByGame.get(gameId).pop();
        //rotating for fun and tests
        roundByGame.put(gameId, roundByGame.get(gameId));
        Integer integer = nextTeamId.get(gameId);
        pop.setTeam(gameRepository.findTeam(teamIds.get(gameId).get(integer % teamIds.get(gameId).size())));
        nextTeamId.put(gameId, ++integer);
        return pop;
    }


    public Boolean addPoints(Long teamId, int points) {
        long teamPoints =  gameRepository.addPoints(teamId, points);
        return teamPoints >= WINNING_POINTS;
    }

    public String startGame(Integer noOfTeams) {
        GameDetails gameDetails = new GameDetails();
        gameDetails.setTeams(createTeams(gameDetails, noOfTeams));
        return startGame(gameDetails);
    }

    private List<Team> createTeams(GameDetails gameDetails, Integer noOfTeams) {
        List<Team> teams = new ArrayList<>(noOfTeams);
        for(int i =0; i< noOfTeams; i++){
            teams.add(createTeam(gameDetails,i));
        }
        return teams;
    }


    private Team createTeam(GameDetails gameDetails, int i){
        Team team = new Team();
        team.setGameDetails(gameDetails);
        team.setColor(collors.get(i).name().toLowerCase());
        return team;
    }
}
