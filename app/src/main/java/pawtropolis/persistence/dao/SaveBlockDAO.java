package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.SaveBlock;

import java.util.Set;

@Repository
public interface SaveBlockDAO extends JpaRepository<SaveBlock, Long> {

    @Query("SELECT gs.blockName FROM SaveBlock gs WHERE gs.player.id = :playerId")
    Set<String> findSaveBlockNamesByPlayerId(@Param("playerId") Long playerId);

    SaveBlock findByPlayer_IdAndBlockName(Long playerId, String sessionName);

    boolean existsByPlayer_IdAndBlockName(Long playerId, String sessionName);
}
