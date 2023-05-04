package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.Player;

import java.util.Optional;

@Repository
public interface PlayerDAO extends JpaRepository<Player, Long> {

    boolean existsByName(String name);
    Optional<Player> findByName(String name);

}
