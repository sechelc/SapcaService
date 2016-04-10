package com.rogames.engine.repository;

import com.rogames.engine.model.GameDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by sechelc on 10.04.2016.
 */
@Repository
@Transactional
public class GameRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String createGame(GameDetails gameDetails) {
        entityManager.persist(gameDetails);
        return gameDetails.getId().toString();
    }
}
