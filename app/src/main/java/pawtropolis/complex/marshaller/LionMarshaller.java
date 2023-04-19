package pawtropolis.complex.marshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.animals.AnimalBuilderDirector;
import pawtropolis.complex.game.animals.domain.LionBO;
import pawtropolis.complex.persistence.entity.Lion;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LionMarshaller implements Marshaller<Lion, LionBO> {
    @Override
    public Lion marshall(LionBO lionBO) {
        return AnimalBuilderDirector.buildLion(lionBO);
    }

    @Override
    public LionBO unmarshall(Lion lion) {
        return AnimalBuilderDirector.buildLionBO(lion);
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
