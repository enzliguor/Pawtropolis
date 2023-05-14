package pawtropolis.game.domain;

import lombok.*;
import pawtropolis.game.domain.doorstate.DoorState;
import pawtropolis.game.domain.doorstate.LockedDoorState;

@Builder
@Getter
public class DoorBO {

    protected final RoomBO roomA;

    protected final RoomBO roomB;
    @Setter
    private DoorState state;

    public boolean isLocked() {
        return this.state instanceof LockedDoorState;
    }

    public RoomBO open(RoomBO currentRoom) {
        return this.state.open(currentRoom);
    }

    public boolean tryToUnlock(ItemBO itemKey) {
        if (isLocked()) {
            return this.state.tryToSwitchState(itemKey);
        }
        return !isLocked();
    }

    public boolean lock(ItemBO itemKey) {
        if (!isLocked()) {
            return this.state.tryToSwitchState(itemKey);
        }
        return isLocked();
    }
}
