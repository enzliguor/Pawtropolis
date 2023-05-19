package pawtropolis.persistence.marshaller.animalmarshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.persistence.marshaller.Marshaller;
import pawtropolis.persistence.utils.AnimalBuilderDirector;
import pawtropolis.game.domain.animals.domain.TigerBO;
import pawtropolis.persistence.entity.animals.Tiger;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TigerMarshaller implements Marshaller<Tiger, TigerBO> {
    @Override
    public Tiger marshall(TigerBO tigerBO) {
        return AnimalBuilderDirector.build(tigerBO);
    }

    @Override
    public TigerBO unmarshall(Tiger tiger) {
        return AnimalBuilderDirector.build(tiger);
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
