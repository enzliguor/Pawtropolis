package pawtropolis.complex.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.domain.PlayerBO;
import pawtropolis.complex.persistence.entity.Player;

@Component
public class PlayerMarshaller implements Marshaller<Player, PlayerBO> {

    private final BagMarshaller bagConverter;
    @Autowired
    private PlayerMarshaller(BagMarshaller bagConverter) {
        this.bagConverter = bagConverter;
    }

    @Override
    public Player marshall(PlayerBO playerBO) {
        return Player.builder()
                .id(playerBO.getId())
                .name(playerBO.getName())
                .lifePoints(playerBO.getLifePoints())
                .bag(this.bagConverter.marshall(playerBO.getBag()))
                .build();
    }

    @Override
    public PlayerBO unmarshall(Player player) {
        return PlayerBO.builder()
                .id(player.getId())
                .name(player.getName())
                .lifePoints(player.getLifePoints())
                .bag(this.bagConverter.unmarshall(player.getBag()))
                .build();
    }

    @Override
    public Class<Player> getEntityClass() {
        return Player.class;
    }

    @Override
    public Class<PlayerBO> getBoClass() {
        return PlayerBO.class;
    }
}
