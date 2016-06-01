package com.rogames.engine.repository;

import com.rogames.engine.entity.GameDetails;
import com.rogames.engine.entity.Round;
import com.rogames.engine.entity.Team;
import com.rogames.engine.model.RoundActions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

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

    @PostConstruct
    public void init(){
        createRound(new Round("nu da cioara de pe gard pe lalala", "3", RoundActions.TOTUL_ESTE_POSIBIL.toString()));
        createRound(new Round("alta intrebare", "5", "deseneaza, mimeaza, vorbeste"));
        createRound(new Round("intrebare", "6", "mima"));
        createRound(new Round("masa", "4", "mima"));
        createRound(new Round("scaun", "4", "mima"));
        createRound(new Round("shalala", "5", "mima"));
        createRound(new Round("asdada", "3", "mima"));
        createRound(new Round("qweqew", "6", "mima"));
        createRound(new Round("inca o intrebare", "6", "mima"));
        createRound(new Round("iendynqkjn", "2", "mima"));
        createRound(new Round("cdcdcdcd", "4", "mima"));
    }

    public GameDetails retrieveGameDetails(Long gameId) {
        return entityManager.find(GameDetails.class, gameId);
    }

    public List<Round> retrieveAllRounds() {
        Query query = entityManager.createQuery("SELECT r FROM Round r");
        return (List<Round>) query.getResultList();
    }

    public Long createRound(Round round) {
        entityManager.persist(round);
        return round.getId();
    }

    public Team findTeam(Long aLong) {
        return entityManager.find(Team.class, aLong);
    }

    public long addPoints(Long teamId, int points) {
        Team team = findTeam(teamId);
        team.addPoints(points);
        entityManager.persist(team);
        return team.getPoints();
    }
}
