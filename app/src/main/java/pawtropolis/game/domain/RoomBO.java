package pawtropolis.game.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
public class RoomBO implements BusinessObject {
    @Setter
    private Long id;
    private final String name;
    private  final Map<ItemBO, Integer> items;
    private final List<AnimalBO> animals;
    private final EnumMap<CardinalPoint, DoorBO> doors;

    protected RoomBO(RoomBOBuilder<?, ?> builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.items = (builder.items != null) ? builder.items : new HashMap<>();
        this.animals = (builder.animals != null) ? builder.animals : new ArrayList<>();
        this.doors = (builder.doors != null) ? builder.doors : new EnumMap<>(CardinalPoint.class);
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
        doorBO.setState(UnlockedDoorStateBO.builder().id(1L).doorBO(doorBO).build());
        this.doors.put(cardinalPoint, doorBO);
        CardinalPoint opposite = cardinalPoint.getOpposite();
        roomBO.linkRoom(opposite, doorBO);
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
        this.doors.put(cardinalPoint, doorBO);
        CardinalPoint opposite = cardinalPoint.getOpposite();
        roomBO.linkRoom(opposite, doorBO);
    }
    public void linkRoom(CardinalPoint cardinalPoint, DoorBO doorBO){
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
