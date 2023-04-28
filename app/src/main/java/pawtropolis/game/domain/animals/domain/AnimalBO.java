package pawtropolis.game.domain.animals.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pawtropolis.game.domain.BusinessObject;

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
}

