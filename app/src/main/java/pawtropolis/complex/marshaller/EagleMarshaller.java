package pawtropolis.complex.marshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.animals.domain.EagleBO;
import pawtropolis.complex.persistence.entity.Eagle;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EagleMarshaller implements Marshaller<Eagle, EagleBO> {

    @Override
    public Eagle marshall(EagleBO animalBO) {
        return Eagle.builder()
                .id(animalBO.getId())
                .favoriteFood(animalBO.getFavoriteFood())
                .age(animalBO.getAge())
                .weight(animalBO.getWeight())
                .joinDate(animalBO.getJoinDate())
                .height(animalBO.getHeight())
                .name(animalBO.getName())
                .wingspan(animalBO.getWingspan())
                .build();
    }

    @Override
    public EagleBO unmarshall(Eagle animal) {
        return EagleBO.builder()
                .id(animal.getId())
                .favoriteFood(animal.getFavoriteFood())
                .age(animal.getAge())
                .weight(animal.getWeight())
                .joinDate(animal.getJoinDate())
                .height(animal.getHeight())
                .name(animal.getName())
                .wingspan(animal.getWingspan())
                .build();
    }

    @Override
    public Class<Eagle> getEntityClass() {
        return Eagle.class;
    }

    @Override
    public Class<EagleBO> getBoClass() {
        return EagleBO.class;
    }

}
