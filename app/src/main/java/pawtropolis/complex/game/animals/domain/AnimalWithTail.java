package pawtropolis.complex.game.animals.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
public abstract class AnimalWithTail extends Animal {
	private double tailLength;

	protected AnimalWithTail(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height, double tailLength) {
		super(name, favoriteFood, age, joinDate, weight, height);
		this.tailLength = tailLength;
	}
}
