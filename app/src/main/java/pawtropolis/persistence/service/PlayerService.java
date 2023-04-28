package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.persistence.entity.Player;
import pawtropolis.persistence.utils.MarshallerManager;

@Service
public class PlayerService extends AbstractService<Player, Long, PlayerBO>{

    @Autowired
    public PlayerService(JpaRepository<Player, Long> dao, MarshallerManager marshallerManager) {
        super(dao, marshallerManager);
    }
}
