package pawtropolis.complex.game.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;


@EqualsAndHashCode
@ToString
@Slf4j
@Entity
@Table(name = "bag")
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private int availableSlot;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "items_in_bag",
            joinColumns = {@JoinColumn(name = "id_bag", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "id_item", referencedColumnName = "id")
    @Column(name = "quantity")
    private final Map<Item, Integer> items = new HashMap<>();

    public Bag() {
    }

    public Bag(int capacity) {
        this.availableSlot = capacity;
    }

    public Map<Item, Integer> getItems() {
        return Map.copyOf(this.items);
    }

    public Map<String, Integer> getItemsName() {
        return this.items.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Map.Entry::getValue
                ));
    }

    public void addItem(Item item) {
        addItem(item, 1);
    }

    public void addItem(Item item, Integer integer) {
        if (item != null && checkItemsFits(item, integer)) {
            this.items.put(item, this.items.getOrDefault(item, 0) + integer);
            this.availableSlot -= item.getSlotsRequired() * integer;
        }
    }

    public Item removeItem(Item item) {
        return removeItem(item, 1);
    }

    public Item removeItem(Item item, Integer integer) {
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

    public Item removeItemByName(String itemName) {
        Item item = findItemByName(itemName);
        return removeItem(item);
    }

    public boolean checkItemFits(Item item) {
        return checkItemsFits(item, 1);
    }

    public boolean checkItemsFits(Item item, Integer integer) {
        return item.getSlotsRequired() * integer <= this.availableSlot;
    }

    public Integer getItemQuantity(Item item) {
        return this.items.get(item);
    }

    public Item findItemByName(String itemName) {
        return this.items.keySet().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElse(null);
    }

}
