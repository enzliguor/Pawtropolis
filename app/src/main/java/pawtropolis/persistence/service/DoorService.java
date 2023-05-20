package pawtropolis.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.persistence.entity.Door;
import pawtropolis.persistence.marshaller.Marshaller;

@Service
public class DoorService extends AbstractService<Door, Long, DoorBO>{
    public DoorService(JpaRepository<Door, Long> dao, Marshaller<Door, DoorBO> marshaller) {
        super(dao, marshaller);
    }
}
