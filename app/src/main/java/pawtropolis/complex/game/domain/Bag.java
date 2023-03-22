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

	public List<Item> getItems(){
		return List.copyOf(this.items);
	}

	public List<String> getItemsName(){
		return this.items.stream()
				.map(Item::getName)
				.toList();
	}

	public void addItem(Item item){
		if(item!=null) {
			this.items.add(item);
			this.availableSlot -= item.getSlotRequired();
		}
	}

	public Item removeItem(Item item) {
		if (item != null) {
			this.items.remove(item);
			this.availableSlot += item.getSlotRequired();
		}
		return item;
	}

	public Item removeItemByName(String itemName) {
		Item item = items.stream()
				.filter(i -> i.getName().equals(itemName))
				.findFirst()
				.orElse(null);
		return removeItem(item);
	}

	public boolean checkItemFits(Item item) {
		return item.getSlotRequired() <= this.availableSlot;
	}
}
