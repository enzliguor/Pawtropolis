package pawtropolis.complex.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.complex.persistence.entity.Room;
@Repository
public interface RoomDAO extends JpaRepository<Room, Long> {

}
