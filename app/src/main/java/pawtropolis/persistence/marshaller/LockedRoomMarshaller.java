package pawtropolis.persistence.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.LockedRoomBO;
import pawtropolis.game.map.util.CardinalPoint;
import pawtropolis.persistence.entity.LockedRoom;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class LockedRoomMarshaller implements Marshaller<LockedRoom, LockedRoomBO>{

    private final ItemMarshaller itemMarshaller;

    private final AnimalMarshaller animalMarshaller;

    @Autowired
    public LockedRoomMarshaller(ItemMarshaller itemMarshaller, AnimalMarshaller animalMarshaller) {
        this.itemMarshaller = itemMarshaller;
        this.animalMarshaller = animalMarshaller;
    }

    @Override
    public LockedRoom marshall(LockedRoomBO businessObject) {
        return LockedRoom.builder()
                .id(businessObject.getId())
                .name(businessObject.getName())
                .items(businessObject.getItems().entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> this.itemMarshaller.marshall(entry.getKey()),
                                Map.Entry::getValue
                        )))
                .animals(this.animalMarshaller.marshallToSet(businessObject.getAnimals()))
                .adjacentRooms(new EnumMap<>(CardinalPoint.class))
                .key(this.itemMarshaller.marshall(businessObject.getKey()))
                .build();
    }

    @Override
    public LockedRoomBO unmarshall(LockedRoom entity) {
        return LockedRoomBO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .items(entity.getItems().entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> this.itemMarshaller.unmarshall(entry.getKey()),
                                Map.Entry::getValue
                        )))
                .animals(this.animalMarshaller.unmarshallFromSet(entity.getAnimals()))
                .adjacentRooms(new EnumMap<>(CardinalPoint.class))
                .key(this.itemMarshaller.unmarshall(entity.getKey()))
                .build();
    }

    @Override
    public Class<LockedRoom> getEntityClass() {
        return LockedRoom.class;
    }

    @Override
    public Class<LockedRoomBO> getBoClass() {
        return LockedRoomBO.class;
    }
}
