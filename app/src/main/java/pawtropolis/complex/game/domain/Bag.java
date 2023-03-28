package pawtropolis.complex.game.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode
@ToString
@Slf4j
@Entity
@Table(name = "bag")
public class Bag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Getter
	@Setter
	private int availableSlot;
	@OneToMany
	@JoinTable(name = "items_in_bag",
			joinColumns = {@JoinColumn(name = "id_bag", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "id_item", referencedColumnName = "id")})
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
			this.availableSlot -= item.getSlotsRequired();
		}
	}

	public Item removeItem(Item item) {
		if (item != null) {
			this.items.remove(item);
			this.availableSlot += item.getSlotsRequired();
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
		return item.getSlotsRequired() <= this.availableSlot;
	}
}
