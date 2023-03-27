package pawtropolis.complex.game.animals.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue(value = "eagle")
public class Eagle extends AnimalWithWings {
    public Eagle(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height, double wingspan) {
        super(name, favoriteFood, age, joinDate, weight, height, wingspan);
    }
}
