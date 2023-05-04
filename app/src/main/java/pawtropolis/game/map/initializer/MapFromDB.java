package pawtropolis.game.map.initializer;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.persistence.service.RoomService;
@Component
public class MapFromDB implements MapInitializer{

    private final RoomService roomService;

    public MapFromDB(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public RoomBO populateMap() {
        return roomService.getCopy(1L);
    }
}
