package pawtropolis.complex.game.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pawtropolis.complex.persistence.entity.Item;
import pawtropolis.complex.game.BusinessObject;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ItemBO implements BusinessObject {

	private Long id;
	private String name;

	private String description;

	private int slotsRequired;

	@Override
	public Item parseToPO() {
		return Item.builder()
				.id(this.getId())
				.name(this.getName())
				.description(this.getDescription())
				.slotsRequired(this.getSlotsRequired())
				.build();
	}
}
