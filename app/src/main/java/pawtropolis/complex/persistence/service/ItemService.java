package pawtropolis.complex.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.complex.game.domain.ItemBO;
import pawtropolis.complex.persistence.entity.Item;
import pawtropolis.complex.utils.MarshallerManager;
@Service
public class ItemService extends AbstractService<Item, Long, ItemBO> {

    @Autowired
    private ItemService(JpaRepository<Item, Long> dao, MarshallerManager marshallerManager) {
        super(dao, marshallerManager);
    }



}
