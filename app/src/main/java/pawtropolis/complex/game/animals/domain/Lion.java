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
@DiscriminatorValue(value = "lion")
public class Lion extends AnimalWithTail {

    public Lion(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height, double tailLength) {
        super(name, favoriteFood, age, joinDate, weight, height, tailLength);
    }
}
