package pawtropolis.persistence.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.SaveBlockBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.persistence.entity.SaveBlock;
@Component
public class SaveBlockMarshaller implements  Marshaller<SaveBlock, SaveBlockBO> {

    private final PlayerMarshaller playerMarshaller;

    private final BagMarshaller bagMarshaller;

    private final RoomMarshaller roomMarshaller;
    @Autowired
    public SaveBlockMarshaller(PlayerMarshaller playerMarshaller, RoomMarshaller roomMarshaller, BagMarshaller bagMarshaller) {
        this.playerMarshaller = playerMarshaller;
        this.roomMarshaller = roomMarshaller;
        this.bagMarshaller = bagMarshaller;
    }

    @Override
    public SaveBlock marshall(SaveBlockBO businessObject) {
        return SaveBlock.builder()
                .id(businessObject.getId())
                .blockName((businessObject.getName()))
                .lifePoints(businessObject.getPlayer().getLifePoints())
                .bag(this.bagMarshaller.marshall(businessObject.getPlayer().getBag()))
                .player(this.playerMarshaller.marshall(businessObject.getPlayer()))
                .currentRoom(this.roomMarshaller.marshall(businessObject.getCurrentRoom()))
                .build();
    }

    @Override
    public SaveBlockBO unmarshall(SaveBlock entity) {
        PlayerBO playerBO = this.playerMarshaller.unmarshall(entity.getPlayer());
        playerBO.setBag(this.bagMarshaller.unmarshall(entity.getBag()));
        playerBO.setLifePoints(entity.getLifePoints());
        return SaveBlockBO.builder()
                .id(entity.getId())
                .name(entity.getBlockName())
                .player(playerBO)
                .currentRoom(this.roomMarshaller.unmarshall(entity.getCurrentRoom()))
                .build();
    }

    @Override
    public Class<SaveBlock> getEntityClass() {
        return SaveBlock.class;
    }

    @Override
    public Class<SaveBlockBO> getBoClass() {
        return SaveBlockBO.class;
    }
}
