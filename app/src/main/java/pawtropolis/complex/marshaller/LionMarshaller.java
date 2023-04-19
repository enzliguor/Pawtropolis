package pawtropolis.complex.marshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.animals.domain.LionBO;
import pawtropolis.complex.persistence.entity.Lion;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LionMarshaller implements Marshaller<Lion, LionBO> {
    @Override
    public Lion marshall(LionBO animalBO) {
        return Lion.builder()
                .id(animalBO.getId())
                .favoriteFood(animalBO.getFavoriteFood())
                .age(animalBO.getAge())
                .weight(animalBO.getWeight())
                .joinDate(animalBO.getJoinDate())
                .height(animalBO.getHeight())
                .name(animalBO.getName())
                .tailLength(animalBO.getTailLength())
                .build();
    }

    @Override
    public LionBO unmarshall(Lion animal) {
        return LionBO.builder()
                .id(animal.getId())
                .favoriteFood(animal.getFavoriteFood())
                .age(animal.getAge())
                .weight(animal.getWeight())
                .joinDate(animal.getJoinDate())
                .height(animal.getHeight())
                .name(animal.getName())
                .tailLength(animal.getTailLength())
                .build();
    }

    @Override
    public Class<Lion> getEntityClass() {
        return Lion.class;
    }

    @Override
    public Class<LionBO> getBoClass() {
        return LionBO.class;
    }
}
