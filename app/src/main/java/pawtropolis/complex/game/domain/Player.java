package pawtropolis.complex.game.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@ToString
@EqualsAndHashCode
@PropertySource(value = "file:application.properties")
public class Player {

	@Value("${DEFAULT_LIFE_POINTS}")
	private int DEFAULT_LIFE_POINTS;
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

	public Player(String name, int lifePoints){
		this();
		this.name = name;
		this.lifePoints = lifePoints;
	}

	public Player(String name){
		this(name, DEFAULT_LIFE_POINTS);
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
