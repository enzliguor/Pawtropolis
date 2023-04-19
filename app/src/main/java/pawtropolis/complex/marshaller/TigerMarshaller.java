package pawtropolis.complex.marshaller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.complex.utils.AnimalBuilderDirector;
import pawtropolis.complex.game.animals.domain.TigerBO;
import pawtropolis.complex.persistence.entity.Tiger;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TigerMarshaller implements Marshaller<Tiger, TigerBO> {
    @Override
    public Tiger marshall(TigerBO tigerBO) {
        return AnimalBuilderDirector.buildTiger(tigerBO);
    }

    @Override
    public TigerBO unmarshall(Tiger tiger) {
        return AnimalBuilderDirector.buildTigerBO(tiger);
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
