package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.persistence.dao.GameSessionDAO;
import pawtropolis.persistence.entity.GameSession;
import pawtropolis.persistence.marshaller.Marshaller;

import java.util.List;

@Service
public class GameSessionService extends AbstractService<GameSession, Long, GameSessionBO> {


    @Autowired
    public GameSessionService(JpaRepository<GameSession, Long> dao, Marshaller<GameSession, GameSessionBO> marshaller) {
        super(dao, marshaller);
    }

    public List<String> findSessionNamesByPlayerId(Long id){
        return ((GameSessionDAO) dao).findSessionNamesByPlayerId(id);
    }

    @Transactional
    public GameSessionBO findByPlayerIdAndSessionName(Long id, String sessionName){
        GameSession gameSession = ((GameSessionDAO) dao).findByPlayer_IdAndSessionName(id, sessionName);
        return this.marshaller.unmarshall(gameSession);
    }

    public boolean existsByPlayerIdAndSessionName(Long playerId, String sessionName){
        return ((GameSessionDAO) dao).existsByPlayer_IdAndSessionName(playerId, sessionName);
    }
}
