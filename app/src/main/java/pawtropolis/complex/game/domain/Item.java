package pawtropolis.complex.game.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class Item {
	@NonNull
	private String name;
	@NonNull
	private String description;
	private int slotRequired;
}
