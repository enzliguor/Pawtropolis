package pawtropolis.complex.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.map.domain.RoomBO;
import pawtropolis.complex.persistence.entity.Room;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RoomMarshaller implements Marshaller<Room, RoomBO> {

    private final ItemMarshaller itemConverter;

    private final AnimalMarshaller animalMarshaller;

    @Autowired
    private RoomMarshaller(ItemMarshaller itemConverter, AnimalMarshaller animalMarshaller) {
        this.itemConverter = itemConverter;
        this.animalMarshaller = animalMarshaller;
    }

    @Override
    public Room marshall(RoomBO roomBO) {
        return Room.builder()
                .id(roomBO.getId())
                .name(roomBO.getName())
                .items(roomBO.getItems().entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> this.itemConverter.marshall(entry.getKey()),
                                Map.Entry::getValue
                        )))
                .animals(this.animalMarshaller.marshall(roomBO.getAnimals()))
                //TODO Implement behavior regarding adjacent rooms
//                .adjacentRooms()
                .build();
    }

    @Override
    public RoomBO unmarshall(Room room) {
        return RoomBO.builder()
                .id(room.getId())
                .name(room.getName())
                .items(room.getItems().entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> this.itemConverter.unmarshall(entry.getKey()),
                                Map.Entry::getValue
                        )))
                .animals(this.animalMarshaller.unmarshall(room.getAnimals()))
                //TODO Implement behavior regarding adjacent rooms
//                .adjacentRooms()
                .build();
    }

    @Override
    public Class<Room> getEntityClass() {
        return Room.class;
    }

    @Override
    public Class<RoomBO> getBoClass() {
        return RoomBO.class;
    }
}
