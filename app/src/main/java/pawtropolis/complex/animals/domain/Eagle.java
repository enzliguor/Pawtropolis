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
public class Eagle extends AnimalWithWings {
    public Eagle(String name, String favoriteFood, int age, LocalDate joinDate, double weight, double height, double wingspan) {
        super(name, favoriteFood, age, joinDate, weight, height, wingspan);
    }
}
