package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.persistence.entity.Room;
import pawtropolis.persistence.utils.MarshallerManager;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoomService extends AbstractService<Room, Long, RoomBO> {

    private  final ItemService itemService;

    @Autowired
    public RoomService(JpaRepository<Room, Long> dao, MarshallerManager marshallerManager, ItemService itemService) {
        super(dao, marshallerManager);
        this.itemService = itemService;
    }

    @Override
    public Room saveOrUpdate(RoomBO roomBO) {
        saveItemsInRoom(roomBO);
        return super.saveOrUpdate(roomBO);
    }

    private void saveItemsInRoom(RoomBO roomBO){
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
