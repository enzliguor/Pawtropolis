package pawtropolis.game.domain.doorstate;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.RoomBO;

@SuperBuilder
public class LockedDoorStateBO extends DoorStateBO {
    @Getter
    private final ItemBO key;

    protected LockedDoorStateBO(DoorBO doorBO, ItemBO key) {
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
            this.doorBO.setState(UnlockedDoorStateBO.builder()
                    .id(1L)
                    .doorBO(this.doorBO)
                    .build());
        }
        return !this.doorBO.isLocked();
    }
}
