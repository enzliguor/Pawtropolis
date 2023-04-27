package pawtropolis.complex.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.complex.persistence.entity.Player;
@Repository
public interface PlayerDAO extends JpaRepository<Player, Long> {
}
