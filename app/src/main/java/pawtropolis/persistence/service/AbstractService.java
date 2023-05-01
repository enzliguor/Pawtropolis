package pawtropolis.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pawtropolis.game.domain.BusinessObject;
import pawtropolis.persistence.entity.EntityDB;
import pawtropolis.persistence.marshaller.Marshaller;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class AbstractService<E extends EntityDB, ID, B extends BusinessObject> {

    protected final JpaRepository<E, ID> dao;

    protected final Marshaller<E, B> marshaller;

    public AbstractService(JpaRepository<E, ID> dao, Marshaller<E, B> marshaller) {
        this.dao = dao;
        this.marshaller = marshaller;
    }

    public E saveOrUpdate(B businessObject){
        if(businessObject == null){
            return null;
        }
        E entity = marshaller.marshall(businessObject);
        return dao.save(entity);
    }
    @Transactional
    public B findById(ID id){
        if(id == null){
            return null;
        }
        Optional<E> entityOpt = dao.findById(id);
        return marshaller.unmarshall(entityOpt.orElse(null));
    }
    @Transactional
    public List<B> findAll(){
        Iterable<E> collection = dao.findAll();
        List<E> list = StreamSupport.stream(collection.spliterator(), true).toList();
        return marshaller.unmarshall(list);
    }

    public void deleteById(ID id){
        dao.deleteById(id);
    }
}
