package pawtropolis.complex.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pawtropolis.complex.exception.MarshallerNotFoundException;
import pawtropolis.complex.game.BusinessObject;
import pawtropolis.complex.marshaller.Marshaller;
import pawtropolis.complex.persistence.entity.EntityDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MarshallerManager {

    private final Map<Class<?>, Marshaller<?, ?>> marshallers;

    private final Map<Class<?>, Marshaller<?, ?>> unmarshallers;

    @Autowired
    private MarshallerManager(ApplicationContext applicationContext) {
        this.marshallers = new HashMap<>();
        this.unmarshallers = new HashMap<>();
        applicationContext.getBeansOfType(Marshaller.class).values().forEach(marshaller -> {
            this.marshallers.put(marshaller.getEntityClass(), marshaller);
            this.unmarshallers.put(marshaller.getBoClass(), marshaller);
        });
    }

    public <T extends EntityDB, E extends BusinessObject> T marshall(E e, Class<T> targetClass) {
        if (e == null) {
            return null;
        }
        Marshaller<T, E> marshaller = (Marshaller<T, E>) this.marshallers.get(targetClass);
        return marshall(e, marshaller, targetClass);
    }

    public <T extends EntityDB, E extends BusinessObject> E unmarshall(T t, Class<E> targetClass) {
        if (t == null) {
            return null;
        }
        Marshaller<T, E> marshaller = (Marshaller<T, E>) this.unmarshallers.get(targetClass);
        return unmarshall(t, marshaller, targetClass);
    }

    private <T extends EntityDB, E extends BusinessObject> T marshall(E e, Marshaller<T, E> marshaller, Class<T> targetClass) {
        if (e == null) {
            return null;
        }
        if (marshaller == null) {
            throw new MarshallerNotFoundException("you don't have a marshaller for this target class");
        } else if (marshaller.getBoClass() != e.getClass() && !marshaller.getBoClass().isAssignableFrom(e.getClass())) {
            throw new MarshallerNotFoundException("you don't have a marshaller that handles this conversion");
        }
        return targetClass.cast(marshaller.marshall(e));
    }

    private <T extends EntityDB, E extends BusinessObject> E unmarshall(T t, Marshaller<T, E> marshaller, Class<E> targetClass) {
        if (t == null) {
            return null;
        }
        if (marshaller == null) {
            throw new MarshallerNotFoundException("you don't have an unmarshaller for this target class");
        } else if (marshaller.getEntityClass() != t.getClass() && !marshaller.getEntityClass().isAssignableFrom(t.getClass())) {
            throw new MarshallerNotFoundException("you don't have an unmarshaller that handles this conversion");
        }
        return targetClass.cast(marshaller.unmarshall(t));
    }

    public <T extends EntityDB, E extends BusinessObject> List<T> marshall(List<E> e, Class<T> targetClass) {
        return e.stream()
                .map(obj -> marshall(obj, targetClass))
                .toList();
    }

    public <T extends EntityDB, E extends BusinessObject> List<E> unmarshall(List<T> t, Class<E> targetClass) {
        return t.stream()
                .map(obj -> unmarshall(obj, targetClass))
                .toList();
    }
}
