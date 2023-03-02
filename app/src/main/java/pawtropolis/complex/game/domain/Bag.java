package pawtropolis.complex.game.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@EqualsAndHashCode
@ToString
@Slf4j
public class Bag {

	private static final int CAPACITY = 20;
	@Getter
	@Setter
	private int availableSlot;
	private final Map<String, Item> items = new HashMap<>();

	public Bag(int capacity){
		this.availableSlot = capacity;
	}

	public Bag(){
		this(CAPACITY);
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

	public void showBagContent() {
		StringBuilder builder = new StringBuilder("In bag: ");

		for (String s: this.items.keySet()) {
			builder.append(s + ", ");
		}
		if (builder.toString().endsWith(", ")) {
			builder.delete(builder.length()-2, builder.length()-1);
		}

		builder.append("   [Available Slot: " + this.availableSlot + "]");
		log.info(builder + "\n");
	}
}
