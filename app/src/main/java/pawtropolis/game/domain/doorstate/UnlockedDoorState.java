package pawtropolis.game.domain.doorstate;

import lombok.experimental.SuperBuilder;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.RoomBO;

@SuperBuilder
public class UnlockedDoorState extends DoorState {

    protected UnlockedDoorState(DoorBO doorBO) {
        super(doorBO);
    }

    @Override
    public RoomBO open(RoomBO currentRoom) {
        if(currentRoom == null){
            return null;
        }
        return (this.doorBO.getRoomA().equals(currentRoom)) ? this.doorBO.getRoomB() : this.doorBO.getRoomA();
    }

    @Override
    public boolean tryToSwitchState(ItemBO itemKey) {
        this.doorBO.setState(LockedDoorState.builder()
                .doorBO(this.doorBO)
                .key(itemKey)
                .build());
        return this.doorBO.isLocked();
    }
}