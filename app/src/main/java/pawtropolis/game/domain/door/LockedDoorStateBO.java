package pawtropolis.game.domain.door;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.RoomBO;


@Getter
@Slf4j
@SuperBuilder
public class LockedDoorStateBO extends DoorStateBO{

    private Long id;

    private final ItemBO key;

    @Override
    public RoomBO open(RoomBO currentRoom) {
       return  currentRoom;
    }

    @Override
    public boolean switchState(ItemBO itemKey) {
        return tryToUnlock(itemKey);
    }
    private boolean tryToUnlock(ItemBO itemKey){
        if(this.key.equals(itemKey)){
            this.doorBO.setState(UnlockedDoorStateBO.builder()
                    .doorBO(this.doorBO)
                    .build());
            return true;
        }
        return false;
    }
}
