package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.persistence.dao.GameSessionDAO;
import pawtropolis.persistence.entity.GameSession;
import pawtropolis.persistence.marshaller.Marshaller;

import java.util.Set;

@Service
public class GameSessionService extends AbstractService<GameSession, Long, GameSessionBO> {


    private final PlayerService playerService;
    private final RoomService roomService;

    @Autowired
    public GameSessionService(JpaRepository<GameSession, Long> dao, Marshaller<GameSession, GameSessionBO> marshaller, PlayerService playerService, RoomService roomService) {
        super(dao, marshaller);
        this.playerService = playerService;
        this.roomService = roomService;
    }

    @Override
    public GameSessionBO saveOrUpdate(GameSessionBO gameSessionBO){
        if(gameSessionBO.isNotPersisted()){
            PlayerBO playerBO = this.playerService.saveOrUpdate(gameSessionBO.getPlayer());
            RoomBO roomBO = this.roomService.saveOrUpdate(gameSessionBO.getCurrentRoom());

            playerBO.setBag(gameSessionBO.getPlayer().getBag());
            gameSessionBO.setPlayer(playerBO);
            gameSessionBO.setCurrentRoom(roomBO);
        }
        return super.saveOrUpdate(gameSessionBO);
    }

    public Set<String> findSessionNamesByPlayerId(Long id){
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
