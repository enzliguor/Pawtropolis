package pawtropolis.complex.game.animals.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pawtropolis.complex.persistence.entity.Animal;
import pawtropolis.complex.game.BusinessObject;

import java.time.LocalDate;


@Getter
@ToString
@EqualsAndHashCode
@SuperBuilder
public abstract class AnimalBO implements BusinessObject {
	private Long id;
	private String name;
	private String favoriteFood;
	private int age;
	private LocalDate joinDate;
	private double weight;
	private double height;

	@Override
	public abstract Animal parseToPO();
}

