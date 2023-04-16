package pawtropolis.complex.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pawtropolis.complex.game.animals.domain.AnimalBO;
import pawtropolis.complex.game.animals.domain.TigerBO;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@DiscriminatorValue(value = "tiger")
public class Tiger extends AnimalWithTail{

    @Override
    public AnimalBO parseToBO() {
        return TigerBO.builder()
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
