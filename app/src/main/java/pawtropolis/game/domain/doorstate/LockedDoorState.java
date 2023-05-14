package pawtropolis.game.domain.doorstate;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.RoomBO;

@SuperBuilder
public class LockedDoorState extends DoorState {
    @Getter
    private final ItemBO key;

    protected LockedDoorState(DoorBO doorBO, ItemBO key) {
        super(doorBO);
        this.key = key;
    }
    @Override
    public RoomBO open(RoomBO currentRoom) {
        return null;
    }

    @Override
    public boolean tryToSwitchState(ItemBO itemKey) {
        if(this.key.equals(itemKey)){
            this.doorBO.setState(new UnlockedDoorState(this.doorBO));
        }
        return !this.doorBO.isLocked();
    }
}
