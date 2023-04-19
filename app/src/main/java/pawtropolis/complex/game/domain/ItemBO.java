package pawtropolis.complex.game.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ItemBO{

	private Long id;
	private String name;

	private String description;

	private int slotsRequired;
}
