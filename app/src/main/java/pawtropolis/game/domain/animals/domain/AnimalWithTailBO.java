package pawtropolis.game.domain.animals.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class AnimalWithTailBO extends AnimalBO {

	private final double tailLength;
}
