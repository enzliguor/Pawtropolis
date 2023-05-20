package pawtropolis.game.domain;

import lombok.*;
import pawtropolis.game.domain.doorstate.DoorStateBO;
import pawtropolis.game.domain.doorstate.LockedDoorStateBO;

@Builder
@Getter
@Setter
public class DoorBO implements BusinessObject{

    private Long id;

    private RoomBO roomA;

    private RoomBO roomB;
    @Setter
    private DoorStateBO state;

    public boolean isLocked() {
        return this.state instanceof LockedDoorStateBO;
    }

    public RoomBO open(RoomBO currentRoom) {
        return this.state.open(currentRoom);
    }

    public boolean tryToUnlock(ItemBO itemKey) {
        return isLocked() && this.state.switchState(itemKey);
    }

    public boolean tryToLock(ItemBO itemKey) {
        return !isLocked() && this.state.switchState(itemKey);
    }
}
