package pawtropolis.persistence.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.doorstate.DoorStateBO;
import pawtropolis.persistence.entity.DoorState;

import java.util.HashMap;
import java.util.Map;

@Component
public class DoorStateMarshaller implements Marshaller<DoorState, DoorStateBO> {

    private final Map<Class<?>, Marshaller<DoorState, DoorStateBO>> doorStateMarshallers;

    @Autowired
    public DoorStateMarshaller(ApplicationContext applicationContext) {
        this.doorStateMarshallers = new HashMap<>();
        applicationContext.getBeansOfType(Marshaller.class).values().stream()
                .filter(marshaller -> DoorState.class.isAssignableFrom(marshaller.getEntityClass()))
                .forEach(marshaller -> {
                    this.doorStateMarshallers.put(marshaller.getEntityClass(), marshaller);
                    this.doorStateMarshallers.put(marshaller.getBoClass(), marshaller);
                });
    }

    @Override
    public DoorState marshall(DoorStateBO businessObject) {
        if(businessObject == null){
            return null;
        }
        Marshaller<DoorState, DoorStateBO> marshaller = this.doorStateMarshallers.get(businessObject.getClass());
        return marshaller.marshall(businessObject);
    }

    @Override
    public DoorStateBO unmarshall(DoorState entity) {
        if(entity == null){
            return null;
        }
        Marshaller<DoorState, DoorStateBO> marshaller = this.doorStateMarshallers.get(entity.getClass());
        return marshaller.unmarshall(entity);
    }

    @Override
    public Class<DoorState> getEntityClass() {
        return DoorState.class;
    }

    @Override
    public Class<DoorStateBO> getBoClass() {
        return DoorStateBO.class;
    }
}
