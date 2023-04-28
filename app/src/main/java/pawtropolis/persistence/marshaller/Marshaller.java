package pawtropolis.persistence.marshaller;

import pawtropolis.game.domain.BusinessObject;
import pawtropolis.persistence.entity.EntityDB;

public interface Marshaller<E extends EntityDB, B extends BusinessObject> {

    E marshall(B businessObject);

    B unmarshall(E entity);

    Class<E> getEntityClass();

    Class<B> getBoClass();
}
