package pawtropolis.complex.game.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "slots_required")
	private int slotsRequired;

	public Item(String name, String description, int slotsRequired) {
		this.name = name;
		this.description = description;
		this.slotsRequired = slotsRequired;
	}
}
