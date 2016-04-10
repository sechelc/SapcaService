package com.rogames.engine.repository;

import com.rogames.engine.entity.GameDetails;
import com.rogames.engine.entity.Round;
import com.rogames.engine.entity.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by sechelc on 10.04.2016.
 */
@Repository
@Transactional
public class GameRepository {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Transactional
    public GameDetails createGame(GameDetails gameDetails) {
        entityManager.persist(gameDetails);
        return gameDetails;
    }

    public GameDetails retrieveGameDetails(Long gameId) {
        return entityManager.find(GameDetails.class, gameId);
    }

    public List<Round> retrieveAllRounds() {
        Query query = entityManager.createQuery("SELECT r FROM Round r");
        return (List<Round>) query.getResultList();
    }

    public void createRound(Round anything) {
        entityManager.persist(anything);
    }

    public Team findTeam(Long aLong) {
        return entityManager.find(Team.class, aLong);
    }
}
