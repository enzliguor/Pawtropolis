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
public class Tiger extends AnimalWithTail {
    public Tiger(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height, double tailLength) {
        super(name, favoriteFood, age, joinDate, weight, height, tailLength);
    }
}
