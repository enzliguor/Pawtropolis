package pawtropolis.persistence.marshaller;

import org.springframework.stereotype.Component;
import pawtropolis.game.domain.doorstate.UnlockedDoorStateBO;
import pawtropolis.persistence.entity.UnlockedDoorState;
@Component
public class UnlockedDoorStateMarshaller implements Marshaller<UnlockedDoorState, UnlockedDoorStateBO> {

    @Override
    public UnlockedDoorState marshall(UnlockedDoorStateBO businessObject) {
        return UnlockedDoorState.builder()
                .id(businessObject.getId())
                .build();
    }

    @Override
    public UnlockedDoorStateBO unmarshall(UnlockedDoorState entity) {
        return UnlockedDoorStateBO.builder()
                .id(entity.getId())
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
