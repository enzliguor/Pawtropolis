package pawtropolis.game.domain.door;

import lombok.experimental.SuperBuilder;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.RoomBO;

@SuperBuilder
public class UnlockedDoorStateBO extends DoorStateBO {

    @Override
    public RoomBO open(RoomBO currentRoom) {
        if(currentRoom == null){
            return null;
        }
        return (this.doorBO.getRoomA().equals(currentRoom)) ? this.doorBO.getRoomB() : this.doorBO.getRoomA();
    }

    @Override
    public boolean switchState(ItemBO itemKey) {
        this.doorBO.setState(LockedDoorStateBO.builder()
                .doorBO(this.doorBO)
                .key(itemKey)
                .build());
        return true;
    }
}
