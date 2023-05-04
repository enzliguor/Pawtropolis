package pawtropolis.persistence.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.persistence.entity.GameSession;
@Component
public class GameSessionMarshaller implements  Marshaller<GameSession, GameSessionBO> {

    private final PlayerMarshaller playerMarshaller;

    private final BagMarshaller bagMarshaller;

    private final RoomMarshaller roomMarshaller;
    @Autowired
    public GameSessionMarshaller(PlayerMarshaller playerMarshaller, RoomMarshaller roomMarshaller, BagMarshaller bagMarshaller) {
        this.playerMarshaller = playerMarshaller;
        this.roomMarshaller = roomMarshaller;
        this.bagMarshaller = bagMarshaller;
    }

    @Override
    public GameSession marshall(GameSessionBO businessObject) {
        return GameSession.builder()
                .id(businessObject.getId())
                .sessionName((businessObject.getSessionName()))
                .lifePoints(businessObject.getPlayer().getLifePoints())
                .bag(this.bagMarshaller.marshall(businessObject.getPlayer().getBag()))
                .player(this.playerMarshaller.marshall(businessObject.getPlayer()))
                .currentRoom(this.roomMarshaller.marshall(businessObject.getCurrentRoom()))
                .build();
    }

    @Override
    public GameSessionBO unmarshall(GameSession entity) {
        PlayerBO playerBO = this.playerMarshaller.unmarshall(entity.getPlayer());
        playerBO.setBag(this.bagMarshaller.unmarshall(entity.getBag()));
        playerBO.setLifePoints(entity.getLifePoints());
        return GameSessionBO.builder()
                .id(entity.getId())
                .sessionName(entity.getSessionName())
                .player(playerBO)
                .currentRoom(this.roomMarshaller.unmarshall(entity.getCurrentRoom()))
                .build();
    }

    @Override
    public Class<GameSession> getEntityClass() {
        return GameSession.class;
    }

    @Override
    public Class<GameSessionBO> getBoClass() {
        return GameSessionBO.class;
    }
}
