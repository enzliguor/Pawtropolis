package pawtropolis.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.LockedRoomBO;
import pawtropolis.persistence.entity.LockedRoom;
import pawtropolis.persistence.marshaller.Marshaller;
@Service
public class LockedRoomService extends AbstractService<LockedRoom, Long, LockedRoomBO> {

    public LockedRoomService(JpaRepository<LockedRoom, Long> dao, Marshaller<LockedRoom, LockedRoomBO> marshaller) {
        super(dao, marshaller);
    }
}
