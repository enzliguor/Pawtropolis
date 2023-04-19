package pawtropolis.complex.marshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.animals.domain.TigerBO;
import pawtropolis.complex.persistence.entity.Tiger;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TigerMarshaller implements Marshaller<Tiger, TigerBO> {
    @Override
    public Tiger marshall(TigerBO animalBO) {
        return Tiger.builder()
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
    public TigerBO unmarshall(Tiger animal) {
        return TigerBO.builder()
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
    public Class<Tiger> getEntityClass() {
        return Tiger.class;
    }

    @Override
    public Class<TigerBO> getBoClass() {
        return TigerBO.class;
    }
}
