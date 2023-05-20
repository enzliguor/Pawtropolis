package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.door.DoorState;
@Repository
public interface DoorStateDAO extends JpaRepository<DoorState, Long> {
}
