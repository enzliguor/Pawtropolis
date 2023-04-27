package pawtropolis.complex.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.complex.game.domain.BagBO;
import pawtropolis.complex.persistence.entity.Bag;
import pawtropolis.complex.utils.MarshallerManager;
@Service
public class BagService extends AbstractService<Bag, Long, BagBO>{

    @Autowired
    private BagService(JpaRepository<Bag, Long> dao, MarshallerManager marshallerManager) {
        super(dao, marshallerManager);
    }
}
