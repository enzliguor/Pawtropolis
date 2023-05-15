package pawtropolis.persistence.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.map.util.CardinalPoint;
import pawtropolis.persistence.entity.Door;
import pawtropolis.persistence.entity.Room;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RoomMarshaller implements Marshaller<Room, RoomBO> {

    private final ItemMarshaller itemMarshaller;

    private final AnimalMarshaller animalMarshaller;

    private final DoorMarshaller doorMarshaller;

    @Autowired
    public RoomMarshaller(ItemMarshaller itemMarshaller, AnimalMarshaller animalMarshaller, DoorMarshaller doorMarshaller) {
        this.itemMarshaller = itemMarshaller;
        this.animalMarshaller = animalMarshaller;
        this.doorMarshaller = doorMarshaller;
    }


    @Override
    public Room marshall(RoomBO roomBO) {
        return marshallAllLinkedRoomBO(roomBO);
    }

    private Room marshallAllLinkedRoomBO(RoomBO roomBO) {
        Map<RoomBO, Room> convertedRooms = marshallAllLinkedRoomBO(roomBO, new HashMap<>(), new HashMap<>());
        return convertedRooms.get(roomBO);
    }

    private Map<RoomBO, Room> marshallAllLinkedRoomBO(RoomBO roomBO, Map<RoomBO, Room> convertedRooms, Map<DoorBO, Door> convertedDoors) {
        if(!convertedRooms.containsKey(roomBO)){
            Room sourceRoom = marshallSingleRoomBO(roomBO);
            convertedRooms.put(roomBO, sourceRoom);

            roomBO.getDoors().forEach((cardinal, doorBO) -> {
                if(!convertedDoors.containsKey(doorBO)){
                    Door door = this.doorMarshaller.marshall(doorBO);
                    convertedDoors.put(doorBO, door);

                    RoomBO destinationRoomBO = doorBO.getRoomA().equals(roomBO)? doorBO.getRoomB() : doorBO.getRoomA();
                    marshallAllLinkedRoomBO(destinationRoomBO, convertedRooms, convertedDoors);

                    Room destinationRoom = convertedRooms.get(destinationRoomBO);
                    door.setRoomA(convertedRooms.get(doorBO.getRoomA()));
                    door.setRoomB(convertedRooms.get(doorBO.getRoomB()));
                    linkRooms(sourceRoom, cardinal, door, destinationRoom);
                }
            });
        }
        return convertedRooms;
    }

    private void linkRooms(Room sourceRoom, CardinalPoint cardinalPoint, Door door, Room destinationRoom){
        sourceRoom.getDoors().put(cardinalPoint, door);
        destinationRoom.getDoors().put(cardinalPoint.getOpposite(), door);
    }

    public Room marshallSingleRoomBO(RoomBO roomBO) {
        return Room.builder()
                .id(roomBO.getId())
                .name(roomBO.getName())
                .items(roomBO.getItems().entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> this.itemMarshaller.marshall(entry.getKey()),
                                Map.Entry::getValue
                        )))
                .doors(new EnumMap<>(CardinalPoint.class))
                .animals(this.animalMarshaller.marshallToSet(roomBO.getAnimals()))
                .build();
    }

    @Override
    public RoomBO unmarshall(Room room) {
        return unmarshallAllLinkedRooms(room);
    }

    private RoomBO unmarshallAllLinkedRooms(Room room) {
        Map<Room, RoomBO> convertedRooms = unmarshallAllLinkedRooms(room, new HashMap<>(), new HashMap<>());
        return convertedRooms.get(room);
    }

    private Map<Room, RoomBO> unmarshallAllLinkedRooms(Room room, Map<Room, RoomBO> convertedRooms, Map<Door, DoorBO> convertedDoors) {
        if(!convertedRooms.containsKey(room)){
            RoomBO sourceRoom = unmarshallSingleRoom(room);
            convertedRooms.put(room, sourceRoom);

            room.getDoors().forEach((cardinal, door) -> {
                if(!convertedDoors.containsKey(door)){
                    DoorBO doorBO = this.doorMarshaller.unmarshall(door);
                    convertedDoors.put(door, doorBO);

                    Room destinationRoom = door.getRoomA().equals(room)? door.getRoomB() : door.getRoomA();
                    unmarshallAllLinkedRooms(destinationRoom, convertedRooms, convertedDoors);

                    doorBO.setRoomA(convertedRooms.get(door.getRoomA()));
                    doorBO.setRoomB(convertedRooms.get(door.getRoomB()));

                    RoomBO destinationRoomBO = convertedRooms.get(destinationRoom);
                    sourceRoom.linkRoom(cardinal, doorBO);
                    destinationRoomBO.linkRoom(cardinal.getOpposite(), doorBO);
                }
            });
        }
        return convertedRooms;
    }


    public RoomBO unmarshallSingleRoom(Room room) {
        return RoomBO.builder()
                .id(room.getId())
                .name(room.getName())
                .items(room.getItems().entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> this.itemMarshaller.unmarshall(entry.getKey()),
                                Map.Entry::getValue
                        )))
                .animals(this.animalMarshaller.unmarshallFromSet(room.getAnimals()))
                .doors(new EnumMap<>(CardinalPoint.class))
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
