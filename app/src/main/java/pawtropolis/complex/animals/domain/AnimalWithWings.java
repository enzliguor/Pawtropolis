package pawtropolis.complex.animals.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
public abstract class AnimalWithWings extends Animal {
	private double wingspan;

	protected AnimalWithWings(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height, double wingspan) {
		super(name, favoriteFood, age, joinDate, weight, height);
		this.wingspan = wingspan;
	}
}
