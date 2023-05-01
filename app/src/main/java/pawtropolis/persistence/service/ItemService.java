package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.persistence.entity.Item;
import pawtropolis.persistence.marshaller.Marshaller;

@Service
public class ItemService extends AbstractService<Item, Long, ItemBO> {

    @Autowired
    public ItemService(JpaRepository<Item, Long> dao, Marshaller<Item, ItemBO> marshaller) {
        super(dao, marshaller);
    }
}
