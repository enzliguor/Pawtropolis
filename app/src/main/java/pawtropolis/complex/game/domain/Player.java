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

	public List<String> getBagContent(){
		return this.bag.getAllItemsName();
	}

	public boolean checkItemFitsinBag(Item item){
		return this.bag.checkItemFits(item);
	}
	public int getAvailableSlot(){
		return this.bag.getAvailableSlot();
	}

	public boolean addItem(Item item){
		return this.bag.addItem(item);
	}

	public Item removeItemByName(String itemName) {
		return this.bag.removeItemByName(itemName);
	}

	public void setBagCapacity(int bagCapacity){
		this.bag.setAvailableSlot(bagCapacity);
	}
}
