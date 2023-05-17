package pawtropolis.game.domain.doorstate;

import lombok.experimental.SuperBuilder;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.util.GameUtility;

@SuperBuilder
public class UnlockedDoorStateBO extends DoorStateBO {

    protected UnlockedDoorStateBO(DoorBO doorBO) {
        super(doorBO);
    }

    @Override
    public RoomBO open() {
        RoomBO currentRoom = GameUtility.getCurrentRoom();
        if(currentRoom == null){
            return null;
        }
        return (this.doorBO.getRoomA().equals(currentRoom)) ? this.doorBO.getRoomB() : this.doorBO.getRoomA();
    }
}
