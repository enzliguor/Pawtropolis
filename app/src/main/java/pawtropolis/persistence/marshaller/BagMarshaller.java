package pawtropolis.persistence.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.BagBO;
import pawtropolis.persistence.entity.Bag;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BagMarshaller implements Marshaller<Bag, BagBO> {

    private final ItemMarshaller itemConverter;

    @Autowired
    private BagMarshaller(ItemMarshaller itemConverter) {
        this.itemConverter = itemConverter;
    }

    @Override
    public Bag marshall(BagBO bagBO) {
        return Bag.builder()
                .id(bagBO.getId())
                .availableSlot(bagBO.getAvailableSlot())
                .items(bagBO.getItems().entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> this.itemConverter.marshall(entry.getKey()),
                                Map.Entry::getValue
                        )))
                .build();
    }

    @Override
    public BagBO unmarshall(Bag bag) {
        return BagBO.builder()
                .id(bag.getId())
                .availableSlot(bag.getAvailableSlot())
                .items(bag.getItems().entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> this.itemConverter.unmarshall(entry.getKey()),
                                Map.Entry::getValue
                        )))
                .build();
    }

    @Override
    public Class<Bag> getEntityClass() {
        return Bag.class;
    }

    @Override
    public Class<BagBO> getBoClass() {
        return BagBO.class;
    }
}
