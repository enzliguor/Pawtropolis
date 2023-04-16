package pawtropolis.complex.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pawtropolis.complex.game.animals.domain.AnimalBO;
import pawtropolis.complex.game.animals.domain.EagleBO;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@DiscriminatorValue(value = "eagle")
public class Eagle extends AnimalWithWings{
    @Override
    public AnimalBO parseToBO() {
        return EagleBO.builder()
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
