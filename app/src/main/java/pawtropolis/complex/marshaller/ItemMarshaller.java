package pawtropolis.complex.marshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.domain.ItemBO;
import pawtropolis.complex.persistence.entity.Item;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMarshaller implements Marshaller<Item, ItemBO> {

    @Override
    public Item marshall(ItemBO itemBO) {
        return Item.builder()
                .id(itemBO.getId())
                .name(itemBO.getName())
                .description(itemBO.getDescription())
                .slotsRequired(itemBO.getSlotsRequired())
                .build();
    }

    @Override
    public ItemBO unmarshall(Item item) {
        return ItemBO.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .slotsRequired(item.getSlotsRequired())
                .build();
    }

    @Override
    public Class<Item> getEntityClass() {
        return Item.class;
    }

    @Override
    public Class<ItemBO> getBoClass() {
        return ItemBO.class;
    }
}
