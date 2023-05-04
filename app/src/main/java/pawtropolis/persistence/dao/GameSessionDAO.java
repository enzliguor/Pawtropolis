package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.GameSession;

import java.util.List;

@Repository
public interface GameSessionDAO extends JpaRepository<GameSession, Long> {

    @Query("SELECT gs.sessionName FROM GameSession gs WHERE gs.player.id = :playerId")
    List<String> findSessionNamesByPlayerId(@Param("playerId") Long playerId);

    GameSession findByPlayer_IdAndSessionName(Long playerId, String sessionName);

    boolean existsByPlayer_IdAndSessionName(Long playerId, String sessionName);
}
