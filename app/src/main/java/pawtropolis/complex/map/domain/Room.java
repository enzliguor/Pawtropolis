package pawtropolis.complex.map.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.complex.animals.domain.Animal;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.map.util.CardinalPoint;

import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
@Slf4j
public class Room {
	@NonNull
	@Getter
	@Setter
	private String name;
	private final Map<String, Item> items = new HashMap<>();
	private final Map<Class<? extends Animal>, List<Animal>> animals = new HashMap<>();

	private final EnumMap<CardinalPoint, Room> adjacentRooms = new EnumMap<>(CardinalPoint.class);

	public Room getAdjacentRoom(CardinalPoint cardinalPoint) {
		return this.adjacentRooms.get(cardinalPoint);
	}

	public Item findItemByName(String nameItem) {
		return this.items.get(nameItem);
	}

	public boolean removeItem(Item item) {
		Item itemTemp = this.items.remove(item.getName());
		return itemTemp != null;
	}

	public boolean addItem(Item item) {
		if(item != null){
			this.items.put(item.getName(), item);
			return true;
		}
		return false;
	}

	public void addAllItems(List<Item> items) {
		items.forEach(this::addItem);
	}

	public void addAnimal(Animal animal) {
		this.animals.computeIfAbsent(animal.getClass(), k-> new ArrayList<>()).add(animal);
	}

	public void addAllAnimals(List<Animal> animals) {
		animals.forEach(this::addAnimal);
	}

	public boolean removeAnimal(Animal animal) {
		return animals.get(animal.getClass()).removeIf(a -> a.equals(animal));
	}

	public void linkRoom(CardinalPoint cardinalPoint, Room room){
		this.adjacentRooms.put(cardinalPoint, room);
		CardinalPoint opposite = cardinalPoint.getOpposite();
		Room oppositeRoom = room.getAdjacentRoom(opposite);
		if(oppositeRoom != this){
			room.linkRoom(opposite, this);
		}
	}
	public List<String> getItemsName(){
		return List.of(items.keySet().toString());
	}

	public  Map<Class<? extends Animal>, List<String>> getAnimalsName(){
		return  this.animals.entrySet().stream()
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						entry -> entry.getValue()
								.stream()
								.map(Animal::getName)
								.toList()
				));
	}

}
