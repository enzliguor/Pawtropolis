package pawtropolis.complex.game.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@EqualsAndHashCode
@ToString
@Slf4j
public class Bag {

	private static final int DEFAULT_CAPACITY = 20;
	@Getter
	@Setter
	private int availableSlot;
	private final Map<String, Item> items = new HashMap<>();

	public Bag(){
		this(DEFAULT_CAPACITY);
	}

	public Bag(int capacity){
		this.availableSlot = capacity;
	}

	public boolean addItem(Item item) {
		if (item == null) return false;
		if (item.getSlotRequired() > this.availableSlot) {
			log.info("Your Bag is too full! \nFree up " + (item.getSlotRequired() - this.availableSlot) + " slots to get this item\n");
			return false;
		}
		this.items.put(item.getName(),item);
		this.availableSlot -= item.getSlotRequired();
		return true;
	}

	public Item removeItemByName(String itemName) {
		Item item = this.items.remove(itemName);
		if (item != null) {
			this.availableSlot += item.getSlotRequired();
		}
		return item;
	}

	public List<String> getItems(){
		return List.of(items.keySet().toString());
	}
}
