package pawtropolis.complex.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pawtropolis.complex.game.domain.BagBO;
import pawtropolis.complex.game.domain.ItemBO;
import pawtropolis.complex.persistence.entity.Bag;
import pawtropolis.complex.utils.MarshallerManager;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BagService extends AbstractService<Bag, Long, BagBO>{

    private  final ItemService itemService;

    @Autowired
    public BagService(JpaRepository<Bag, Long> dao, MarshallerManager marshallerManager, ItemService itemService) {
        super(dao, marshallerManager);
        this.itemService=itemService;
    }

    @Override
    public Bag saveOrUpdate(BagBO bagBO) {
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
            itemsToSave.forEach((key, value) -> {
                bagBO.removeItem(key, value);
                bagBO.addItem(
                        marshallerManager.unmarshall(itemService.saveOrUpdate(key), ItemBO.class)
                        , value);
            });
        }
    }
}
