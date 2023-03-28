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
	@OneToMany
	@JoinTable(name = "items_in_room",
			joinColumns = {@JoinColumn(name = "id_item", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "id_room", referencedColumnName = "id")})
	private final List<Item> items = new ArrayList<>();
	@OneToMany
	@JoinTable(name = "animals_in_room",
			joinColumns = {@JoinColumn(name = "id_animal", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "id_room", referencedColumnName = "id")})
	private final List<Animal> animals = new ArrayList<>();
	@ManyToMany
	@JoinTable(name= "linked_rooms",
			joinColumns = {
					@JoinColumn(name = "id_room", referencedColumnName = "id")},
			inverseJoinColumns = {
					@JoinColumn(name = "id_adjacent_room", referencedColumnName = "id")
			})
	@MapKeyEnumerated(value = EnumType.STRING)
	private final Map<CardinalPoint, Room> adjacentRooms = new EnumMap<>(CardinalPoint.class);

	public Room(String name) {
		this.name = name;
	}

	public Room getAdjacentRoom(CardinalPoint cardinalPoint) {
		return this.adjacentRooms.get(cardinalPoint);
	}

	public List<Item> getItems(){
		return List.copyOf(this.items);
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}

	public void addItem(Item item) {
		this.items.add(item);
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

	public  List<Animal> getAnimals(){
		return List.copyOf(this.animals);
	}

	public Item findItemByName(String itemName) {
		return this.items.stream()
				.filter(i -> i.getName().equals(itemName))
				.findFirst()
				.orElse(null);
	}

	public List<String> getItemsName() {
		return this.items.stream()
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
