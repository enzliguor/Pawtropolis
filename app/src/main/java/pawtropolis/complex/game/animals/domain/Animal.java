package pawtropolis.complex.game.animals.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name = "specie",
		discriminatorType = DiscriminatorType.STRING
)
@Table(name = "animal")
public abstract class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name="favorite_food")
	private String favoriteFood;
	@Column(name = "age")
	private int age;
	@Column(name = "join_date")
	private LocalDate joinDate;
	@Column(name = "weight")
	private double weight;
	@Column(name = "height")
	private double height;

	protected Animal(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height) {
		this.name = name;
		this.favoriteFood = favoriteFood;
		this.age = age;
		this.joinDate = joinDate;
		this.weight = weight;
		this.height = height;
	}
}

