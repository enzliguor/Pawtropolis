package pawtropolis.complex.game.animals.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Setter
@Entity
public abstract class AnimalWithWings extends Animal {
	@Column(name = "wingspan")
	private double wingspan;

	protected AnimalWithWings(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height, double wingspan) {
		super(name, favoriteFood, age, joinDate, weight, height);
		this.wingspan = wingspan;
	}
}
