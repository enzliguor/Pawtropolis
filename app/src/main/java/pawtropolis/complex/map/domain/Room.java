package pawtropolis.complex.map.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.complex.animals.domain.Animal;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.map.util.CardinalPoint;

import java.util.*;

@EqualsAndHashCode
@ToString
@Slf4j
public class Room {
	private final RoomDescription roomDescription;
	private final EnumMap<CardinalPoint, Room> adjacentRooms = new EnumMap<>(CardinalPoint.class);

	public Room(String roomName){
		this.roomDescription = new RoomDescription(roomName);
	}

	public Room getAdjacentRoom(CardinalPoint cardinalPoint) {
		return this.adjacentRooms.get(cardinalPoint);
	}

	public RoomDescription getRoomDescription() {
		return this.roomDescription;
	}

	public Item findItemByName(String nameItem) {
		return this.roomDescription.findItemByName(nameItem);
	}

	public boolean removeItem(Item item) {
		return this.roomDescription.removeItem(item);
	}

	public boolean addItem(Item item) {
		return this.roomDescription.addItem(item);
	}

	public void addAllItems(List<Item> items) {
		this.roomDescription.addAllItems(items);
	}

	public void addAnimal(Animal animal) {
		this.roomDescription.addAnimal(animal);
	}

	public void addAllAnimals(List<Animal> animals) {
		this.roomDescription.addAllAnimals(animals);
	}

	public boolean removeAnimal(Animal animal) {
		return this.roomDescription.removeAnimal(animal);
	}

	public void linkRoom(CardinalPoint cardinalPoint, Room room){
		this.adjacentRooms.put(cardinalPoint, room);
		CardinalPoint opposite = cardinalPoint.getOpposite();
		Room oppositeRoom = room.getAdjacentRoom(opposite);
		if(oppositeRoom != this){
			room.linkRoom(opposite, this);
		}
	}

}
