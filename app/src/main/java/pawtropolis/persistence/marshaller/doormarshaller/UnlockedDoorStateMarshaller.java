package pawtropolis.persistence.marshaller.doormarshaller;

import org.springframework.stereotype.Component;
import pawtropolis.game.domain.door.UnlockedDoorStateBO;
import pawtropolis.persistence.entity.door.UnlockedDoorState;
import pawtropolis.persistence.marshaller.Marshaller;

@Component
public class UnlockedDoorStateMarshaller implements Marshaller<UnlockedDoorState, UnlockedDoorStateBO> {

    @Override
    public UnlockedDoorState marshall(UnlockedDoorStateBO businessObject) {
        return UnlockedDoorState.builder()
                .id(1L)
                .build();
    }

    @Override
    public UnlockedDoorStateBO unmarshall(UnlockedDoorState entity) {
        return UnlockedDoorStateBO.builder()
                .build();
    }

    @Override
    public Class<UnlockedDoorState> getEntityClass() {
        return UnlockedDoorState.class;
    }

    @Override
    public Class<UnlockedDoorStateBO> getBoClass() {
        return UnlockedDoorStateBO.class;
    }
}
