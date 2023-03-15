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
	private final List<Item> items = new ArrayList<>();
	private final List<Animal> animals = new ArrayList<>();

	private final Map<CardinalPoint, Room> adjacentRooms = new EnumMap<>(CardinalPoint.class);

	public Room getAdjacentRoom(CardinalPoint cardinalPoint) {
		return this.adjacentRooms.get(cardinalPoint);
	}

	public Item findItemByName(String itemName) {
		return this.items.stream()
				.filter(i -> i.getName().equals(itemName))
				.findFirst()
				.orElse(null);
	}

	public boolean removeItem(Item item) {
		 return this.items.remove(item);
	}

	public boolean addItem(Item item) {
		if(item != null){
			return this.items.add(item);
		}
		return false;
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

	public void linkRoom(CardinalPoint cardinalPoint, Room room){
		this.adjacentRooms.put(cardinalPoint, room);
		CardinalPoint opposite = cardinalPoint.getOpposite();
		Room oppositeRoom = room.getAdjacentRoom(opposite);
		if(oppositeRoom != this){
			room.linkRoom(opposite, this);
		}
	}
	public List<String> getItemsName(){
		return this.items.stream()
				.map(Item::getName)
				.toList();
	}

	public  Map<Class<? extends Animal>, List<String>> getAnimalsName(){
		return  this.animals.stream()
				.collect(Collectors.groupingBy(
						Animal::getClass,
						Collectors.mapping(Animal::getName, Collectors.toList())
				));
	}
}
