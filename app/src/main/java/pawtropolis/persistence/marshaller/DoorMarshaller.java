package pawtropolis.persistence.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.doorstate.DoorStateBO;
import pawtropolis.persistence.entity.Door;

@Component
public class DoorMarshaller implements Marshaller<Door, DoorBO> {

    private final DoorStateMarshaller doorStateMarshaller;

    @Autowired
    public DoorMarshaller(DoorStateMarshaller doorStateMarshaller) {
        this.doorStateMarshaller = doorStateMarshaller;
    }

    @Override
    public Door marshall(DoorBO businessObject) {
        return Door.builder()
                .id(businessObject.getId())
                .state(this.doorStateMarshaller.marshall(businessObject.getState()))
                .build();
    }

    @Override
    public DoorBO unmarshall(Door entity) {
        DoorStateBO doorStateBO = this.doorStateMarshaller.unmarshall(entity.getState());
        DoorBO doorBO = DoorBO.builder()
                .id(entity.getId())
                .state(doorStateBO)
                .build();
        doorStateBO.setDoorBO(doorBO);
        return doorBO;
    }

    @Override
    public Class<Door> getEntityClass() {
        return Door.class;
    }

    @Override
    public Class<DoorBO> getBoClass() {
        return DoorBO.class;
    }
}
