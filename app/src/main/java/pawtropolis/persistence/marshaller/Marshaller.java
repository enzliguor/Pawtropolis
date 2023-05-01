package pawtropolis.persistence.marshaller;

import pawtropolis.game.domain.BusinessObject;
import pawtropolis.persistence.entity.EntityDB;

import java.util.List;

public interface Marshaller<E extends EntityDB, B extends BusinessObject> {

    E marshall(B businessObject);

    B unmarshall(E entity);

    Class<E> getEntityClass();

    Class<B> getBoClass();

    default List<E> marshall(List<B> businessObjects) {
        return businessObjects.stream().map(this::marshall).toList();
    }

    default List<B> unmarshall(List<E> entities) {
        return entities.stream().map(this::unmarshall).toList();
    }
}
