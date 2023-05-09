package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.LockedRoom;

@Repository
public interface LockedRoomDAO extends JpaRepository<LockedRoom, Long> {
}
