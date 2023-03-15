package pawtropolis.complex.game.domain;

import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode
public class Player {
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private int lifePoints;
	private final Bag bag;

	public Player() {
		this.bag = new Bag();
	}

	public Player(int lifePoints){
		this();
		this.lifePoints = lifePoints;
	}

	public Player(String name){
		this();
		this.name = name;
	}

	public List<Item> getBagContent(){
		return this.bag.getItems();
	}

	public int getAvailableSlot(){
		return this.bag.getAvailableSlot();
	}

	public void addItem(Item item){
		this.bag.addItem(item);
	}

	public Item removeItem(Item item) {
		return this.bag.removeItem(item);
	}

	public void setBagCapacity(int bagCapacity){
		this.bag.setAvailableSlot(bagCapacity);
	}
}
