package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pawtropolis.game.domain.SaveBlockBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.persistence.dao.SaveBlockDAO;
import pawtropolis.persistence.entity.SaveBlock;
import pawtropolis.persistence.marshaller.Marshaller;

import java.util.Set;

@Service
public class SaveBlockService extends AbstractService<SaveBlock, Long, SaveBlockBO> {


    private final PlayerService playerService;
    private final RoomService roomService;

    @Autowired
    public SaveBlockService(JpaRepository<SaveBlock, Long> dao, Marshaller<SaveBlock, SaveBlockBO> marshaller, PlayerService playerService, RoomService roomService) {
        super(dao, marshaller);
        this.playerService = playerService;
        this.roomService = roomService;
    }

    @Override
    public SaveBlockBO saveOrUpdate(SaveBlockBO saveBlockBO){
        if(saveBlockBO.isNotPersisted()){
            PlayerBO playerBO = this.playerService.saveOrUpdate(saveBlockBO.getPlayer());
            RoomBO roomBO = this.roomService.saveOrUpdate(saveBlockBO.getCurrentRoom());

            playerBO.setBag(saveBlockBO.getPlayer().getBag());
            saveBlockBO.setPlayer(playerBO);
            saveBlockBO.setCurrentRoom(roomBO);
        }
        return super.saveOrUpdate(saveBlockBO);
    }

    public Set<String> findBlockNamesByPlayerId(Long id){
        return ((SaveBlockDAO) dao).findSaveBlockNamesByPlayerId(id);
    }

    @Transactional
    public SaveBlockBO findByPlayerIdAndBlockName(Long id, String sessionName){
        SaveBlock saveBlock = ((SaveBlockDAO) dao).findByPlayer_IdAndBlockName(id, sessionName);
        return this.marshaller.unmarshall(saveBlock);
    }

    public boolean existsByPlayerIdAndBlockName(Long playerId, String sessionName){
        return ((SaveBlockDAO) dao).existsByPlayer_IdAndBlockName(playerId, sessionName);
    }
}
