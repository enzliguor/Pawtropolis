package pawtropolis.complex.game.animals.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@SuperBuilder
public abstract class AnimalWithWingsBO extends AnimalBO {
	private final double wingspan;
}
