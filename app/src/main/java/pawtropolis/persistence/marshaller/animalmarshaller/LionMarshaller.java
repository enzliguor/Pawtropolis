package pawtropolis.persistence.marshaller.animalmarshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.persistence.marshaller.Marshaller;
import pawtropolis.persistence.utils.AnimalBuilderDirector;
import pawtropolis.game.domain.animals.domain.LionBO;
import pawtropolis.persistence.entity.animals.Lion;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LionMarshaller implements Marshaller<Lion, LionBO> {
    @Override
    public Lion marshall(LionBO lionBO) {
        return AnimalBuilderDirector.build(lionBO);
    }

    @Override
    public LionBO unmarshall(Lion lion) {
        return AnimalBuilderDirector.build(lion);
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
