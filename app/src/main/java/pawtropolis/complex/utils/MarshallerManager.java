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

    private final Map<Class<?>, Marshaller<Object, Object>> marshallers;

    private final Map<Class<?>, Marshaller<Object, Object>> unmarshallers;

    @Autowired
    private MarshallerManager(ApplicationContext applicationContext) {
        this.marshallers = new HashMap<>();
        this.unmarshallers = new HashMap<>();
        applicationContext.getBeansOfType(Marshaller.class).values().forEach(marshaller -> {
            this.marshallers.put(marshaller.getBoClass(), marshaller);
            this.unmarshallers.put(marshaller.getEntityClass(), marshaller);
        });
    }

    public Object marshall(Object obj) {
        if (obj == null) {
            return null;
        }
        Marshaller<Object, Object> marshaller = this.marshallers.get(obj.getClass());
        return marshall(obj, marshaller);
    }

    public Object unmarshall(Object obj) {
        if (obj == null) {
            return null;
        }
        Marshaller<Object, Object> marshaller = this.unmarshallers.get(obj.getClass());
        return unmarshall(obj, marshaller);
    }

    public Object marshall(Object obj, Marshaller<Object, Object> marshaller) {
        if (marshaller == null || obj == null || marshaller.getBoClass() != obj.getClass()) {
            return null;
        }
        return marshaller.marshall(obj);
    }

    public Object unmarshall(Object obj, Marshaller<Object, Object> marshaller) {
        if (marshaller == null || obj == null || marshaller.getEntityClass() != obj.getClass()) {
            return null;
        }
        return marshaller.unmarshall(obj);
    }

    public List<Object> marshall(List<Object> objects) {
        return objects.stream()
                .map(this::marshall)
                .toList();
    }

    public List<Object> unmarshall(List<Object> objects) {
        return objects.stream()
                .map(this::unmarshall)
                .toList();
    }
}
