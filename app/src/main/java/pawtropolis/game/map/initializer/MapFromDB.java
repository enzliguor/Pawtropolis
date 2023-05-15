package pawtropolis.game.map.initializer;

import pawtropolis.game.domain.RoomBO;
import pawtropolis.persistence.service.RoomService;

import java.util.HashSet;
import java.util.Set;

public class MapFromDB implements MapInitializer{

    private final RoomService roomService;

    public MapFromDB(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public RoomBO populateMap() {
        RoomBO roomBO = roomService.findById(1L);
        if(roomBO == null) return null;
        return getCopy(roomBO, new HashSet<>());
    }
    private RoomBO getCopy(RoomBO roomBO, Set<RoomBO> visitedRooms){
        if(!visitedRooms.contains(roomBO)){
            roomBO.setId(null);
            roomBO.getAnimals().forEach(animal -> animal.setId(null));
            visitedRooms.add(roomBO);
            roomBO.getDoors().forEach(((cardinalPoint, doorBO) -> {
                doorBO.setId(null);
                getCopy(doorBO.getRoomA(), visitedRooms);
                getCopy(doorBO.getRoomB(), visitedRooms);
            }));
        }
        return roomBO;
    }
}
