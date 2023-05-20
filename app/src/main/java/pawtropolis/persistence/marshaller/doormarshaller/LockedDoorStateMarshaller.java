package pawtropolis.persistence.marshaller.doormarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.door.LockedDoorStateBO;
import pawtropolis.persistence.entity.door.LockedDoorState;
import pawtropolis.persistence.marshaller.ItemMarshaller;
import pawtropolis.persistence.marshaller.Marshaller;

@Component
public class LockedDoorStateMarshaller implements Marshaller<LockedDoorState, LockedDoorStateBO> {

    private final ItemMarshaller itemMarshaller;

    @Autowired
    public LockedDoorStateMarshaller(ItemMarshaller itemMarshaller) {
        this.itemMarshaller = itemMarshaller;
    }

    @Override
    public LockedDoorState marshall(LockedDoorStateBO businessObject) {
        return LockedDoorState.builder()
                .id(businessObject.getId())
                .itemKey(this.itemMarshaller.marshall(businessObject.getKey()))
                .build();
    }

    @Override
    public LockedDoorStateBO unmarshall(LockedDoorState entity) {
        return LockedDoorStateBO.builder()
                .id(entity.getId())
                .key(this.itemMarshaller.unmarshall(entity.getItemKey()))
                .build();
    }

    @Override
    public Class<LockedDoorState> getEntityClass() {
        return LockedDoorState.class;
    }

    @Override
    public Class<LockedDoorStateBO> getBoClass() {
        return LockedDoorStateBO.class;
    }
}
