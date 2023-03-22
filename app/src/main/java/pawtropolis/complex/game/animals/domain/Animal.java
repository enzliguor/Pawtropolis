package pawtropolis.complex.game.animals.domain;

import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public abstract class Animal {
	@NonNull
	private String name;
	@NonNull
	private String favoriteFood;
	private int age;
	@NonNull
	private LocalDate joinDate;
	private double weight;
	private double height;

}

