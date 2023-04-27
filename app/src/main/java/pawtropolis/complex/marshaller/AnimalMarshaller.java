package pawtropolis.complex.marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.animals.domain.AnimalBO;
import pawtropolis.complex.persistence.entity.Animal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimalMarshaller implements Marshaller<Animal, AnimalBO> {

    private final Map<Class<?>, Marshaller<Animal, AnimalBO>> animalmarshallers;

    @Autowired
    private AnimalMarshaller(ApplicationContext applicationContext) {
        this.animalmarshallers = new HashMap<>();
        applicationContext.getBeansOfType(Marshaller.class).values().stream()
                .filter(marshaller -> Animal.class.isAssignableFrom(marshaller.getEntityClass()) ||
                        AnimalBO.class.isAssignableFrom(marshaller.getBoClass()))
                .forEach(marshaller -> {
                    this.animalmarshallers.put(marshaller.getEntityClass(), marshaller);
                    this.animalmarshallers.put(marshaller.getBoClass(), marshaller);
                });
    }

    @Override
    public Animal marshall(AnimalBO animalBO) {
        if (animalBO == null) {
            return null;
        }
        Marshaller<Animal, AnimalBO> marshaller = this.animalmarshallers.get(animalBO.getClass());
        return (marshaller != null)? marshaller.marshall(marshaller.getBoClass().cast(animalBO)) : null;
    }

    @Override
    public AnimalBO unmarshall(Animal animal) {
        if (animal == null) {
            return null;
        }
        Marshaller<Animal, AnimalBO> marshaller = this.animalmarshallers.get(animal.getClass());
        return marshaller.unmarshall(marshaller.getEntityClass().cast(animal));

    }

    public List<Animal> marshall(List<AnimalBO> animalBoList) {
        return animalBoList.stream()
                .map(this::marshall)
                .toList();
    }

    public List<AnimalBO> unmarshall(List<Animal> animals) {
        return animals.stream()
                .map(this::unmarshall)
                .toList();
    }

    @Override
    public Class<Animal> getEntityClass() {
        return Animal.class;
    }

    @Override
    public Class<AnimalBO> getBoClass() {
        return AnimalBO.class;
    }
}
