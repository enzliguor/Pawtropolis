package pawtropolis.game.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ItemBO implements BusinessObject {

	private Long id;
	private String name;

	private String description;

	private int slotsRequired;
}
