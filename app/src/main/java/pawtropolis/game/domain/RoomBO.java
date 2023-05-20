package pawtropolis.game.domain;

import lombok.*;
import pawtropolis.game.domain.animals.domain.AnimalBO;
import pawtropolis.game.domain.doorstate.LockedDoorStateBO;
import pawtropolis.game.domain.doorstate.UnlockedDoorStateBO;
import pawtropolis.game.map.util.CardinalPoint;
import pawtropolis.game.util.GameUtility;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(exclude = "doors")
@ToString(exclude = "doors")
@Builder
public class RoomBO implements BusinessObject {
    @Setter
    private Long id;
    private final String name;
    private  final Map<ItemBO, Integer> items;
    private final List<AnimalBO> animals;
    private final EnumMap<CardinalPoint, DoorBO> doors;

    private RoomBO(Long id, String name, Map<ItemBO, Integer> items, List<AnimalBO> animals, EnumMap<CardinalPoint, DoorBO> doors) {
        this.id = id;
        this.name = name;
        this.items = (items != null) ? items : new HashMap<>();
        this.animals = (animals != null) ? animals : new ArrayList<>();
        this.doors = (doors != null) ? doors : new EnumMap<>(CardinalPoint.class);
    }

    public DoorBO getDoor(CardinalPoint cardinalPoint) {
        return this.doors.get(cardinalPoint);
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

    public void linkRoomWithUnlockedDoor(CardinalPoint cardinalPoint, RoomBO roomBO) {
        DoorBO doorBO = DoorBO.builder()
                .roomA(this)
                .roomB(roomBO)
                .build();
        doorBO.setState(UnlockedDoorStateBO.builder().doorBO(doorBO).build());
        addDoor(cardinalPoint, doorBO);
        CardinalPoint opposite = cardinalPoint.getOpposite();
        roomBO.addDoor(opposite, doorBO);
    }
    public void linkRoomWithLockedDoor(CardinalPoint cardinalPoint, RoomBO roomBO, ItemBO itemKey){
        DoorBO doorBO = DoorBO.builder()
                .roomA(this)
                .roomB(roomBO)
                .build();
        doorBO.setState(LockedDoorStateBO.builder()
                .doorBO(doorBO)
                .key(itemKey)
                .build());
        addDoor(cardinalPoint, doorBO);
        CardinalPoint opposite = cardinalPoint.getOpposite();
        roomBO.addDoor(opposite, doorBO);
    }
    public void addDoor(CardinalPoint cardinalPoint, DoorBO doorBO){
        this.doors.put(cardinalPoint, doorBO);
    }

    public List<AnimalBO> getAnimals() {
        return List.copyOf(this.animals);
    }

    public ItemBO findItemByName(String itemName) {
        return GameUtility.findItemByName(itemName, this.items.keySet());
    }

    public Integer getItemQuantity(ItemBO item) {
        return this.items.get(item);
    }

    public Map<String, Integer> getItemsNameAndQuantity() {
        return GameUtility.getItemsNameAndQuantity(this.items);
    }

    public Map<Class<? extends AnimalBO>, List<String>> getAnimalsName() {
        return this.animals.stream()
                .collect(Collectors.groupingBy(
                        AnimalBO::getClass,
                        Collectors.mapping(AnimalBO::getName, Collectors.toList())
                ));
    }
}
