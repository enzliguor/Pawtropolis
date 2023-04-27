package pawtropolis.complex.marshaller;

import pawtropolis.complex.game.BusinessObject;
import pawtropolis.complex.persistence.entity.EntityDB;

public interface Marshaller<E extends EntityDB, B extends BusinessObject> {

    E marshall(B businessObject);

    B unmarshall(E entity);

    Class<E> getEntityClass();

    Class<B> getBoClass();
}
