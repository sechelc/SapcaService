package com.rogames.engine;

import com.rogames.engine.model.GameDetails;
import com.rogames.engine.model.GameStats;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;

/**
 * Created by sechelc on 10.04.2016.
 */
public class GameEngineTest {

    @InjectMocks
    private GameEngineService gameEngineService;

    @Before
    public void init(){
        gameEngineService = new GameEngineService();
    }

    @Test
    public void startGameTest(){
        GameDetails gameDetails = new GameDetails();
        String gameId = gameEngineService.startGame(gameDetails);
        assertNotNull(gameId);

        GameStats gameStats = gameEngineService.getGameStats(gameId);
    }
}
