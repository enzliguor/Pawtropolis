package pawtropolis.complex.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pawtropolis.complex.marshaller.Marshaller;

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

    public <T, E> T marshall(E e, Class<T> targetClass) {
        if (e == null) {
            return null;
        }
        Marshaller<T, E> marshaller = (Marshaller<T, E>) this.marshallers.get(targetClass);
        return marshall(e, marshaller, targetClass);
    }

    public <T, E> E unmarshall(T t, Class<E> targetClass) {
        if (t == null) {
            return null;
        }
        Marshaller<T, E> marshaller = (Marshaller<T, E>) this.unmarshallers.get(targetClass);
        return unmarshall(t, marshaller, targetClass);
    }

    private <T, E> T marshall(E e, Marshaller<T, E> marshaller, Class<T> targetClass) {
        if (e == null) {
            return null;
        }
        return marshaller.marshall(obj);
    }

    private <T, E> E unmarshall(T t, Marshaller<T, E> marshaller, Class<E> targetClass) {
        if (t == null) {
            return null;
        }
        return marshaller.unmarshall(obj);
    }

    public <T, E> List<T> marshall(List<E> e, Class<T> targetClass) {
        return e.stream()
                .map(obj -> marshall(obj, targetClass))
                .toList();
    }

    public <T, E> List<E> unmarshall(List<T> t, Class<E> targetClass) {
        return t.stream()
                .map(obj -> unmarshall(obj, targetClass))
                .toList();
    }
}
