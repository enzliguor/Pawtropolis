package pawtropolis.game.domain;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ItemBO implements BusinessObject {
	@Setter
	private Long id;

	private String name;

	private String description;

	private int slotsRequired;
}
