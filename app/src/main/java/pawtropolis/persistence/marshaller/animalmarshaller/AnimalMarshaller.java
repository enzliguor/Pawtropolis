package pawtropolis.persistence.marshaller.animalmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.animals.domain.AnimalBO;
import pawtropolis.persistence.entity.animals.Animal;
import pawtropolis.persistence.marshaller.Marshaller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AnimalMarshaller implements Marshaller<Animal, AnimalBO> {

    private final Map<Class<?>, Marshaller<Animal, AnimalBO>> animalMarshallers;

    @Autowired
    private AnimalMarshaller(ApplicationContext applicationContext) {
        this.animalMarshallers = new HashMap<>();
        applicationContext.getBeansOfType(Marshaller.class).values().stream()
                .filter(marshaller -> Animal.class.isAssignableFrom(marshaller.getEntityClass()))
                .forEach(marshaller -> {
                    this.animalMarshallers.put(marshaller.getEntityClass(), marshaller);
                    this.animalMarshallers.put(marshaller.getBoClass(), marshaller);
                });
    }

    @Override
    public Animal marshall(AnimalBO animalBO) {
        if (animalBO == null) {
            return null;
        }
        Marshaller<Animal, AnimalBO> marshaller = this.animalMarshallers.get(animalBO.getClass());
        return (marshaller != null)? marshaller.marshall(marshaller.getBoClass().cast(animalBO)) : null;
    }

    @Override
    public AnimalBO unmarshall(Animal animal) {
        if (animal == null) {
            return null;
        }
        Marshaller<Animal, AnimalBO> marshaller = this.animalMarshallers.get(animal.getClass());
        return marshaller.unmarshall(marshaller.getEntityClass().cast(animal));

    }

    @Override
    public List<Animal> marshall(List<AnimalBO> animalBoList) {
        return animalBoList.stream()
                .map(this::marshall)
                .toList();
    }
    @Override
    public List<AnimalBO> unmarshall(List<Animal> animals) {
        return animals.stream()
                .map(this::unmarshall)
                .toList();
    }

    public Set<Animal> marshallToSet(List<AnimalBO> animalBoList) {
        return animalBoList.stream()
                .map(this::marshall)
                .collect(Collectors.toSet());
    }

    public List<AnimalBO> unmarshallFromSet(Set<Animal> animals) {
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
