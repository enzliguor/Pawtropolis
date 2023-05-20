package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.door.Door;

@Repository
public interface DoorDAO extends JpaRepository<Door, Long> {
}
