package pawtropolis.complex.game.animals.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pawtropolis.complex.persistence.entity.Animal;
import pawtropolis.complex.persistence.entity.Lion;


@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@SuperBuilder
public class LionBO extends AnimalWithTailBO {

    @Override
    public Animal parseToPO() {
        return Lion.builder()
                .id(this.getId())
                .favoriteFood(this.getFavoriteFood())
                .age(this.getAge())
                .weight(this.getWeight())
                .joinDate(this.getJoinDate())
                .height(this.getHeight())
                .name(this.getName())
                .tailLength(this.getTailLength())
                .build();
    }
}
