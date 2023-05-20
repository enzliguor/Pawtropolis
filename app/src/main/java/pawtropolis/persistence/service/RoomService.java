package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.persistence.entity.Room;
import pawtropolis.persistence.marshaller.Marshaller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoomService extends AbstractService<Room, Long, RoomBO> {

    private  final ItemService itemService;

    @Autowired
    public RoomService(JpaRepository<Room, Long> dao, Marshaller<Room, RoomBO> marshallerManager, ItemService itemService) {
        super(dao, marshallerManager);
        this.itemService = itemService;
    }

    @Override
    public RoomBO saveOrUpdate(RoomBO roomBO) {
        saveNewItemsInAllRooms(roomBO, new ArrayList<>());
        return super.saveOrUpdate(roomBO);
    }

    public void saveNewItemsInAllRooms(RoomBO roomBO, List<RoomBO> rooms){
        if(!rooms.contains(roomBO)){
            saveNewItemsInRoom(roomBO);
            rooms.add(roomBO);
            roomBO.getDoors().forEach((cardinal, door)-> {
                saveNewItemsInAllRooms(door.getRoomA(), rooms);
                saveNewItemsInAllRooms(door.getRoomB(), rooms);
            });
        }
    }

    public void saveNewItemsInRoom(RoomBO roomBO){
        if(roomBO == null){
            return;
        }
        Map<ItemBO, Integer> itemsToSave = roomBO.getItems().entrySet().stream()
                .filter(entry -> entry.getKey().getId() == null)
                .collect(Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue));
        if (itemsToSave.size()>0){
            itemsToSave.forEach((key, value) -> key.setId(itemService.saveOrUpdate(key).getId()));
        }
    }
}
