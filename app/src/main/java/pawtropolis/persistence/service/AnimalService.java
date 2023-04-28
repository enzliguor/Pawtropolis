package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.animals.domain.AnimalBO;
import pawtropolis.persistence.entity.Animal;
import pawtropolis.persistence.utils.MarshallerManager;

@Service
public class AnimalService extends AbstractService<Animal, Long, AnimalBO>{

    @Autowired
    public AnimalService(JpaRepository<Animal, Long> dao, MarshallerManager marshallerManager) {
        super(dao, marshallerManager);
    }
}
