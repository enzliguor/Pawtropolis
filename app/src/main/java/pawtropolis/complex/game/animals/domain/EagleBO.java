package pawtropolis.complex.game.animals.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pawtropolis.complex.persistence.entity.Animal;
import pawtropolis.complex.persistence.entity.Eagle;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@SuperBuilder
public class EagleBO extends AnimalWithWingsBO {

    @Override
    public Animal parseToPO() {
        return Eagle.builder()
                .id(this.getId())
                .favoriteFood(this.getFavoriteFood())
                .age(this.getAge())
                .weight(this.getWeight())
                .joinDate(this.getJoinDate())
                .height(this.getHeight())
                .name(this.getName())
                .wingspan(this.getWingspan())
                .build();
    }
}
