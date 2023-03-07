package pawtropolis.complex.map.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.complex.animals.domain.Animal;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.map.util.CardinalPoint;

import java.util.*;

@RequiredArgsConstructor
@EqualsAndHashCode
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

	public void getRoomDescription() {
		StringBuilder builder = new StringBuilder("You are in " + this.name + ".\nItems: ");

		for (String s: this.items.keySet()) {
			builder.append(s).append(", ");
		}
		if (builder.toString().endsWith(", ")) {
			builder.delete(builder.length()-2, builder.length()-1);
		}

		builder.append("\nNPC: ");

		for (Map.Entry<Class<? extends Animal>, List<Animal>> entry : animals.entrySet()) {
			for (Animal animal : entry.getValue()) {
				builder.append(animal.getName())
						.append("(").append(entry.getKey().getSimpleName()).append(")");
						builder.append(", ");
			}
		}
		if (builder.toString().endsWith(", ")) {
			builder.delete(builder.length()-2, builder.length()-1);
		}

		log.info(builder + "\n");
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
	}

}
