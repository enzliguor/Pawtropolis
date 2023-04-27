package pawtropolis.complex.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.complex.game.animals.domain.AnimalBO;
import pawtropolis.complex.persistence.entity.Animal;
import pawtropolis.complex.utils.MarshallerManager;

@Service
public class AnimalService extends AbstractService<Animal, Long, AnimalBO>{

    @Autowired
    private AnimalService(JpaRepository<Animal, Long> dao, MarshallerManager marshallerManager) {
        super(dao, marshallerManager);
    }
}
