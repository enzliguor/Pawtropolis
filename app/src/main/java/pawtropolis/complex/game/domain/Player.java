package pawtropolis.complex.game.domain;

import lombok.*;

@ToString
@EqualsAndHashCode
public class Player {

	private static final int DEFAULT_LIFE_POINTS = 100;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private int lifePoints;
	private final Bag bag;

	public Player() {
		this(DEFAULT_LIFE_POINTS);
	}

	public Player(int lifePoints){
		this.lifePoints = lifePoints;
		this.bag = new Bag();
	}

	public Player(String name){
		this();
		this.name = name;
	}

	public void showBagContent() {
		this.bag.showBagContent();
	}

	public boolean addItem(Item item) {
		return this.bag.addItem(item);
	}

	public Item removeItemByName(String itemName) {
		return this.bag.removeItemByName(itemName);
	}

	}
