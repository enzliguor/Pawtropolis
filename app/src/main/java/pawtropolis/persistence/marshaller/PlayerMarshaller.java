package pawtropolis.persistence.marshaller;

import org.springframework.stereotype.Component;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.persistence.entity.Player;

@Component
public class PlayerMarshaller implements Marshaller<Player, PlayerBO> {

    @Override
    public Player marshall(PlayerBO playerBO) {
        return Player.builder()
                .id(playerBO.getId())
                .name(playerBO.getName())
                .build();
    }

    @Override
    public PlayerBO unmarshall(Player player) {
        return PlayerBO.builder()
                .id(player.getId())
                .name(player.getName())
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
