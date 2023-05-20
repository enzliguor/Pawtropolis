package pawtropolis.game.domain.door;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pawtropolis.game.domain.BusinessObject;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.RoomBO;

@SuperBuilder
@Getter
@Setter
public abstract class DoorStateBO implements BusinessObject {
    protected DoorBO doorBO;

    protected DoorStateBO(DoorBO doorBO) {
        this.doorBO = doorBO;
    }

    public abstract RoomBO open(RoomBO currentRoom);

    public abstract boolean switchState(ItemBO itemKey);

}
