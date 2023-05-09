package pawtropolis.game.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class LockedRoomBO extends RoomBO {

    private final ItemBO key;


    public RoomBO tryToUnlock(ItemBO itemBO) {
        if (!key.equals(itemBO)) {
            return null;
        }
        return unlock();
    }

    private RoomBO unlock() {
        RoomBO room = RoomBO.builder()
                .name(this.getName())
                .items(this.getItems())
                .animals(this.getAnimals())
                .build();
        this.getAdjacentRooms().forEach(room::linkRoom);

        return room;
    }
}
