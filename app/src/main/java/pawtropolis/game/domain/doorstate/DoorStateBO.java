package pawtropolis.game.domain.doorstate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pawtropolis.game.domain.BusinessObject;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.RoomBO;

@SuperBuilder
@Getter
@Setter
public abstract class DoorStateBO implements BusinessObject {

    protected Long id;
    protected DoorBO doorBO;

    protected DoorStateBO(DoorBO doorBO) {
        this.doorBO = doorBO;
    }

    public abstract RoomBO open();

}
