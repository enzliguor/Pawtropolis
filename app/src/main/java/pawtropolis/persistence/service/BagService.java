package pawtropolis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.game.domain.BagBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.persistence.entity.Bag;
import pawtropolis.persistence.marshaller.Marshaller;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BagService extends AbstractService<Bag, Long, BagBO>{

    private  final ItemService itemService;

    @Autowired
    public BagService(JpaRepository<Bag, Long> dao, Marshaller<Bag, BagBO> marshallerManager, ItemService itemService) {
        super(dao, marshallerManager);
        this.itemService=itemService;
    }

    @Override
    public BagBO saveOrUpdate(BagBO bagBO) {
        saveItemsInBag(bagBO);
        return super.saveOrUpdate(bagBO);
    }

    private void saveItemsInBag(BagBO bagBO) {
        if(bagBO == null){
            return;
        }
        Map<ItemBO, Integer> itemsToSave = bagBO.getItems().entrySet().stream()
                .filter(entry -> entry.getKey().getId() == null)
                .collect(Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue));
        if (itemsToSave.size()>0){
            itemsToSave.forEach((key, value) -> key.setId(itemService.saveOrUpdate(key).getId()));
        }
    }
}
