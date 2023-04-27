package pawtropolis.complex.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pawtropolis.complex.game.BusinessObject;
import pawtropolis.complex.persistence.entity.EntityDB;
import pawtropolis.complex.utils.MarshallerManager;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class AbstractService<E extends EntityDB, ID, B extends BusinessObject> {

    protected final JpaRepository<E, ID> dao;

    protected final MarshallerManager marshallerManager;

    protected final Class<E> entityClass;

    protected final Class<B> businessObjectClass;

    public AbstractService(JpaRepository<E, ID> dao, MarshallerManager marshallerManager) {
        this.dao = dao;
        this.marshallerManager = marshallerManager;
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.businessObjectClass = (Class<B>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    public E saveOrUpdate(B businessObject){
        if(businessObject == null){
            return null;
        }
        E entity = marshallerManager.marshall(businessObject, entityClass);
        return dao.save(entity);
    }
    @Transactional
    public B findById(ID id){
        if(id == null){
            return null;
        }
        Optional<E> entityOpt = dao.findById(id);
        return marshallerManager.unmarshall(entityOpt.orElse(null), businessObjectClass);
    }
    @Transactional
    public List<B> findAll(){
        Iterable<E> collection = dao.findAll();
        List<E> list = StreamSupport.stream(collection.spliterator(), true).toList();
        return marshallerManager.unmarshall(list, businessObjectClass);
    }

    public void deleteById(ID id){
        dao.deleteById(id);
    }
}
