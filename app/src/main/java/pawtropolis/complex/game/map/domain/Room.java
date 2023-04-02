package pawtropolis.complex.game.map.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.complex.game.animals.domain.Animal;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.map.util.CardinalPoint;

import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Slf4j
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "items_in_room",
            joinColumns = {@JoinColumn(name = "id_room", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "id_item", referencedColumnName = "id")
    @Column(name = "quantity")
    private Map<Item, Integer> items = new HashMap<>();
    @OneToMany
    @JoinColumn(name = "id_room", referencedColumnName = "id")
    private final List<Animal> animals = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "linked_rooms",
            joinColumns = {
                    @JoinColumn(name = "id_room", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_adjacent_room", referencedColumnName = "id")
            })
    @MapKeyEnumerated(value = EnumType.STRING)
    @MapKeyColumn(name = "cardinal_point")
    private final Map<CardinalPoint, Room> adjacentRooms = new EnumMap<>(CardinalPoint.class);

    public Room(String name) {
        this.name = name;
    }

    public Room getAdjacentRoom(CardinalPoint cardinalPoint) {
        return this.adjacentRooms.get(cardinalPoint);
    }

    public Map<Item, Integer> getItems() {
        return Map.copyOf(this.items);
    }

    public void removeItem(Item item) {
        removeItems(item, 1);
    }

    public void removeItems(Item item, Integer integer) {
        if (item != null && this.items.containsKey(item) && getItemQuantity(item) >= integer) {
            this.items.put(item, getItemQuantity(item) - integer);
            if (getItemQuantity(item) == 0) {
                this.items.remove(item);
            }
        }
    }

    public void addItem(Item item) {
        addItem(item, 1);
    }

    public void addItem(Item item, Integer integer) {
        if (item != null) {
            this.items.put(item, this.items.getOrDefault(item, 0) + integer);
        }
    }

    public void addAllItems(List<Item> items) {
        items.forEach(this::addItem);
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public void addAllAnimals(List<Animal> animals) {
        animals.forEach(this::addAnimal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void linkRoom(CardinalPoint cardinalPoint, Room room) {
        this.adjacentRooms.put(cardinalPoint, room);
        CardinalPoint opposite = cardinalPoint.getOpposite();
        Room oppositeRoom = room.getAdjacentRoom(opposite);
        if (oppositeRoom != this) {
            room.linkRoom(opposite, this);
        }
    }

    public List<Animal> getAnimals() {
        return List.copyOf(this.animals);
    }

    public Item findItemByName(String itemName) {
        return this.items.keySet().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElse(null);
    }

    public Integer getItemQuantity(Item item) {
        return this.items.get(item);
    }

    public List<String> getItemsName() {
        return this.items.keySet().stream()
                .map(Item::getName)
                .toList();
    }

    public Map<Class<? extends Animal>, List<String>> getAnimalsName() {
        return this.animals.stream()
                .collect(Collectors.groupingBy(
                        Animal::getClass,
                        Collectors.mapping(Animal::getName, Collectors.toList())
                ));
    }
}
