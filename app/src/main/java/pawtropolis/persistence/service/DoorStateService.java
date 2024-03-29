package pawtropolis.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.door.DoorStateBO;
import pawtropolis.persistence.entity.door.DoorState;
import pawtropolis.persistence.marshaller.Marshaller;
@Service
public class DoorStateService extends AbstractService<DoorState, Long, DoorStateBO> {
    public DoorStateService(JpaRepository<DoorState, Long> dao, Marshaller<DoorState, DoorStateBO> marshaller) {
        super(dao, marshaller);
    }
}
