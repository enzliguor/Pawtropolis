package pawtropolis.game.domain.doorstate;

import lombok.experimental.SuperBuilder;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.RoomBO;

@SuperBuilder
public abstract class DoorState {

    protected final DoorBO doorBO;

    protected DoorState(DoorBO doorBO) {
        this.doorBO = doorBO;
    }

    public abstract RoomBO open(RoomBO currentRoom);

    public abstract boolean tryToSwitchState(ItemBO itemKey);

}
