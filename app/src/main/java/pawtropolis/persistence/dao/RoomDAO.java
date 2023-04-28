package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.Room;

@Repository
public interface RoomDAO extends JpaRepository<Room, Long> {

}
