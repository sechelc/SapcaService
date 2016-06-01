package com.rogames.engine;

import com.rogames.engine.entity.Round;
import com.rogames.engine.model.RoundActions;
import com.rogames.engine.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameAdminService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameEngineService gameEngineService;

    public void createRound(String riddleText, String points, RoundActions... roundActions){
        String actions ="";
        for (RoundActions roundAction : roundActions) {
            actions+=", " + roundAction;
        }
        gameRepository.createRound(new Round(riddleText, points, actions));
        gameEngineService.updateRoundList();
    }

    public String createRound(String riddleText, String points,String roundActions){
        Long roundId = gameRepository.createRound(new Round(riddleText, points, roundActions));
        gameEngineService.updateRoundList();
        return roundId.toString();
    }

}
