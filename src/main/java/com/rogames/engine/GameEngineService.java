package com.rogames.engine;

import com.rogames.engine.model.GameDetails;
import com.rogames.engine.model.GameStats;
import com.rogames.engine.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

/**
 * Created by sechelc on 10.04.2016.
 */
@Component
public class GameEngineService {

    @Id
    @Autowired
    private GameRepository gameRepository;

    public String startGame(GameDetails gameDetails) {
        return gameRepository.createGame(gameDetails);
    }

    public GameStats getGameStats(String gameId) {
        return null;
    }
}
