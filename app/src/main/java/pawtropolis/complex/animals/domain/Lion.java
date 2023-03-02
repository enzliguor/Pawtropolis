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
public class Lion extends AnimalWithTail {

    public Lion(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height, double tailLength) {
        super(name, favoriteFood, age, joinDate, weight, height, tailLength);
    }
}
