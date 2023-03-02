package pawtropolis.complex.animals;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.complex.animals.domain.Animal;
import pawtropolis.complex.animals.domain.AnimalWithTail;
import pawtropolis.complex.animals.domain.AnimalWithWings;

import java.util.*;

@NoArgsConstructor
@Slf4j
@ToString
public class ZooController {

private final Map<Class<? extends Animal>, List<Animal>> animals= new HashMap<>();

	public void addAnimal(Animal animal) {
		animals.computeIfAbsent(animal.getClass(), k -> new ArrayList<>()).add(animal);
	}

	@SuppressWarnings("unchecked")
	private <T extends Animal> List<T> getAnimalsByClass(Class<T> clazz) {
		if (animals.get(clazz) != null) {
			return (List<T>) animals.get(clazz);
		}
		return animals.values().stream()
				.flatMap(Collection::stream)
				.filter(clazz::isInstance)
				.map(clazz::cast)
				.toList();
	}

	private List<AnimalWithTail> getAllAnimalsWithTail() {
		return getAnimalsByClass(AnimalWithTail.class);
	}

	public AnimalWithTail getAnimalWithLongestTail() {
		return getAllAnimalsWithTail().stream()
				.max(Comparator.comparing(AnimalWithTail::getTailLength))
				.orElse(null);
	}

	public List<AnimalWithWings> getAllAnimalsWithWings() {
		return getAnimalsByClass(AnimalWithWings.class);
	}

	public AnimalWithWings getAnimalWithWidestWingspan() {
		return getAllAnimalsWithWings().stream()
				.max(Comparator.comparing(AnimalWithWings::getWingspan))
				.orElse(null);
	}

	public <T extends Animal> T getTallestAnimalByClass(Class<T> clazz) {
		return getAnimalsByClass(clazz).stream()
				.max(Comparator.comparing(Animal::getHeight))
				.orElse(null);
	}

	public <T extends Animal> T getShortestAnimalByClass(Class<T> clazz) {
		return getAnimalsByClass(clazz).stream()
				.min(Comparator.comparing(Animal::getHeight))
				.orElse(null);
	}

	public <T extends Animal> T getHeaviestAnimalByClass(Class<T> clazz) {
		return getAnimalsByClass(clazz).stream()
				.max(Comparator.comparing(Animal::getWeight))
				.orElse(null);
	}

	public <T extends Animal> T getLightestAnimalByClass(Class<T> clazz) {
		return getAnimalsByClass(clazz).stream()
				.min(Comparator.comparing(Animal::getWeight))
				.orElse(null);
	}
}
