package pawtropolis.complex.marshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.animals.AnimalBuilderDirector;
import pawtropolis.complex.game.animals.domain.EagleBO;
import pawtropolis.complex.persistence.entity.Eagle;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EagleMarshaller implements Marshaller<Eagle, EagleBO> {

    @Override
    public Eagle marshall(EagleBO eagleBO) {
        return AnimalBuilderDirector.buildEagle(eagleBO);
    }
    @Override
    public EagleBO unmarshall(Eagle eagle) {
        return AnimalBuilderDirector.buildEagleBO(eagle);
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
