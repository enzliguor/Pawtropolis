package pawtropolis.persistence.marshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.persistence.utils.AnimalBuilderDirector;
import pawtropolis.game.domain.animals.domain.EagleBO;
import pawtropolis.persistence.entity.Eagle;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EagleMarshaller implements Marshaller<Eagle, EagleBO> {

    @Override
    public Eagle marshall(EagleBO eagleBO) {
        return AnimalBuilderDirector.build(eagleBO);
    }
    @Override
    public EagleBO unmarshall(Eagle eagle) {
        return AnimalBuilderDirector.build(eagle);
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
