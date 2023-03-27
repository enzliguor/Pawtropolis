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
public abstract class AnimalWithTail extends Animal {
	@Column(name = "tail_length")
	private double tailLength;

	protected AnimalWithTail(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height, double tailLength) {
		super(name, favoriteFood, age, joinDate, weight, height);
		this.tailLength = tailLength;
	}
}
