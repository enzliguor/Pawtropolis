package pawtropolis.complex.game.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode
@ToString
@Slf4j
public class Bag {

	@Getter
	@Setter
	private int availableSlot;
	private final List<Item> items = new ArrayList<>();

	public Bag(){
	}

	public Bag(int capacity){
		this.availableSlot = capacity;
	}

	public boolean addItem(Item item){
		if (item == null) return false;
		this.items.add(item);
		this.availableSlot -= item.getSlotRequired();
		return true;
	}

	public boolean checkItemFits(Item item){
		return item.getSlotRequired() < this.availableSlot;
	}

	public Item removeItemByName(String itemName) {
		Item item = this.items.stream()
				.filter(i -> i.getName().equals(itemName))
				.findFirst()
				.orElse(null);
		if (item != null) {
			this.availableSlot += item.getSlotRequired();
		}
		return item;
	}

	public List<String> getAllItemsName(){
		return this.items.stream()
				.map(Item::getName)
				.toList();
	}
}
