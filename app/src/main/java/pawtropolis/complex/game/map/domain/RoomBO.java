package pawtropolis.complex.game.map.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pawtropolis.complex.game.BusinessObject;
import pawtropolis.complex.game.animals.domain.AnimalBO;
import pawtropolis.complex.game.domain.ItemBO;
import pawtropolis.complex.game.map.util.CardinalPoint;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(exclude = "adjacentRooms")
@ToString(exclude = "adjacentRooms")
@Builder
public class RoomBO implements BusinessObject {
    private Long id;
    private final String name;
    private  final Map<ItemBO, Integer> items;
    private final List<AnimalBO> animals;
    private final EnumMap<CardinalPoint, RoomBO> adjacentRooms;

    private RoomBO(Long id, String name, Map<ItemBO, Integer> items, List<AnimalBO> animals, EnumMap<CardinalPoint, RoomBO> adjacentRooms) {
        this.id = id;
        this.name = name;
        this.items = (items != null) ? items : new HashMap<>();
        this.animals = (animals != null) ? animals : new ArrayList<>();
        this.adjacentRooms = (adjacentRooms != null) ? adjacentRooms : new EnumMap<>(CardinalPoint.class);
    }

    public RoomBO getAdjacentRoom(CardinalPoint cardinalPoint) {
        return this.adjacentRooms.get(cardinalPoint);
    }

    public Map<ItemBO, Integer> getItems() {
        return Map.copyOf(this.items);
    }

    public void removeItem(ItemBO item) {
        removeItems(item, 1);
    }

    public void removeItems(ItemBO item, Integer integer) {
        if (item != null && this.items.containsKey(item) && getItemQuantity(item) >= integer) {
            this.items.put(item, getItemQuantity(item) - integer);
            if (getItemQuantity(item) == 0) {
                this.items.remove(item);
            }
        }
    }

    public void addItem(ItemBO item) {
        addItem(item, 1);
    }

    public void addItem(ItemBO item, Integer integer) {
        if (item != null) {
            this.items.put(item, this.items.getOrDefault(item, 0) + integer);
        }
    }

    public void addAllItems(List<ItemBO> items) {
        items.forEach(this::addItem);
    }

    public void addAnimal(AnimalBO animal) {
        this.animals.add(animal);
    }

    public void addAllAnimals(List<AnimalBO> animals) {
        animals.forEach(this::addAnimal);
    }

    public void removeAnimal(AnimalBO animal) {
        animals.remove(animal);
    }

    public void linkRoom(CardinalPoint cardinalPoint, RoomBO roomBO) {
        this.adjacentRooms.put(cardinalPoint, roomBO);
        CardinalPoint opposite = cardinalPoint.getOpposite();
        RoomBO oppositeRoom = roomBO.getAdjacentRoom(opposite);
        if (oppositeRoom != this) {
            roomBO.linkRoom(opposite, this);
        }
    }

    public List<AnimalBO> getAnimals() {
        return List.copyOf(this.animals);
    }

    public ItemBO findItemByName(String itemName) {
        return this.items.keySet().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElse(null);
    }

    public Integer getItemQuantity(ItemBO item) {
        return this.items.get(item);
    }

    public Map<String, Integer> getItemsName() {
        return this.items.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Map.Entry::getValue
                ));
    }

    public Map<Class<? extends AnimalBO>, List<String>> getAnimalsName() {
        return this.animals.stream()
                .collect(Collectors.groupingBy(
                        AnimalBO::getClass,
                        Collectors.mapping(AnimalBO::getName, Collectors.toList())
                ));
    }
}
