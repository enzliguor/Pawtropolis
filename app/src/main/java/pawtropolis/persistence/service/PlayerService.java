package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.persistence.dao.PlayerDAO;
import pawtropolis.persistence.entity.Player;
import pawtropolis.persistence.marshaller.Marshaller;

@Service
public class PlayerService extends AbstractService<Player, Long, PlayerBO>{
    @Autowired
    public PlayerService(JpaRepository<Player, Long> dao, Marshaller<Player, PlayerBO> marshaller) {
        super(dao, marshaller);
    }

    public boolean existsByName(String name){
        return ((PlayerDAO)dao).existsByName(name);
    }
}
