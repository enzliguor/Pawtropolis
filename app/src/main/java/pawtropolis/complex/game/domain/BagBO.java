package pawtropolis.complex.game.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pawtropolis.complex.game.BusinessObject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class BagBO implements BusinessObject {
    private Long id;

    private int availableSlot;

    private Map<ItemBO, Integer> items;

    private static final int DEFAULT_BAG_CAPACITY = 20;

    private BagBO(Long id, int availableSlot, Map<ItemBO, Integer> items) {
        this.id = id;
        this.availableSlot = (availableSlot != 0) ? availableSlot : DEFAULT_BAG_CAPACITY;
        this.items = (items != null) ? items : new HashMap<>();
    }

    public Map<ItemBO, Integer> getItems() {
        return Map.copyOf(this.items);
    }

    public Map<String, Integer> getItemsName() {
        return this.items.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Map.Entry::getValue
                ));
    }

    public void addItem(ItemBO item) {
        addItem(item, 1);
    }

    public void addItem(ItemBO item, Integer integer) {
        if (item != null && checkItemsFits(item, integer)) {
            this.items.put(item, this.items.getOrDefault(item, 0) + integer);
            this.availableSlot -= item.getSlotsRequired() * integer;
        }
    }

    public ItemBO removeItem(ItemBO item) {
        return removeItem(item, 1);
    }

    public ItemBO removeItem(ItemBO item, Integer integer) {
        if (item != null && this.items.containsKey(item) && getItemQuantity(item) >= integer) {
            this.items.put(item, getItemQuantity(item) - integer);
            if (getItemQuantity(item) == 0) {
                this.items.remove(item);
            }
            this.availableSlot += item.getSlotsRequired() * integer;
            return item;
        }
        return null;
    }

    public ItemBO removeItemByName(String itemName) {
        ItemBO item = findItemByName(itemName);
        return removeItem(item);
    }

    public boolean checkItemFits(ItemBO item) {
        return checkItemsFits(item, 1);
    }

    public boolean checkItemsFits(ItemBO item, Integer integer) {
        return item.getSlotsRequired() * integer <= this.availableSlot;
    }

    public Integer getItemQuantity(ItemBO item) {
        return this.items.get(item);
    }

    public ItemBO findItemByName(String itemName) {
        return this.items.keySet().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElse(null);
    }
}
