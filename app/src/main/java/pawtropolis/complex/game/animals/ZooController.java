package pawtropolis.complex.game.animals;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.complex.game.animals.domain.AnimalBO;
import pawtropolis.complex.game.animals.domain.AnimalWithTailBO;
import pawtropolis.complex.game.animals.domain.AnimalWithWingsBO;

import java.util.*;

@NoArgsConstructor
@Slf4j
@ToString
public class ZooController {

private final Map<Class<? extends AnimalBO>, List<AnimalBO>> animals= new HashMap<>();

	public void addAnimal(AnimalBO animal) {
		animals.computeIfAbsent(animal.getClass(), k -> new ArrayList<>()).add(animal);
	}

	@SuppressWarnings("unchecked")
	private <T extends AnimalBO> List<T> getAnimalsByClass(Class<T> clazz) {
		if (animals.get(clazz) != null) {
			return (List<T>) animals.get(clazz);
		}
		return animals.values().stream()
				.flatMap(Collection::stream)
				.filter(clazz::isInstance)
				.map(clazz::cast)
				.toList();
	}

	private List<AnimalWithTailBO> getAllAnimalsWithTail() {
		return getAnimalsByClass(AnimalWithTailBO.class);
	}

	public AnimalWithTailBO getAnimalWithLongestTail() {
		return getAllAnimalsWithTail().stream()
				.max(Comparator.comparing(AnimalWithTailBO::getTailLength))
				.orElse(null);
	}

	public List<AnimalWithWingsBO> getAllAnimalsWithWings() {
		return getAnimalsByClass(AnimalWithWingsBO.class);
	}

	public AnimalWithWingsBO getAnimalWithWidestWingspan() {
		return getAllAnimalsWithWings().stream()
				.max(Comparator.comparing(AnimalWithWingsBO::getWingspan))
				.orElse(null);
	}

	public <T extends AnimalBO> T getTallestAnimalByClass(Class<T> clazz) {
		return getAnimalsByClass(clazz).stream()
				.max(Comparator.comparing(AnimalBO::getHeight))
				.orElse(null);
	}

	public <T extends AnimalBO> T getShortestAnimalByClass(Class<T> clazz) {
		return getAnimalsByClass(clazz).stream()
				.min(Comparator.comparing(AnimalBO::getHeight))
				.orElse(null);
	}

	public <T extends AnimalBO> T getHeaviestAnimalByClass(Class<T> clazz) {
		return getAnimalsByClass(clazz).stream()
				.max(Comparator.comparing(AnimalBO::getWeight))
				.orElse(null);
	}

	public <T extends AnimalBO> T getLightestAnimalByClass(Class<T> clazz) {
		return getAnimalsByClass(clazz).stream()
				.min(Comparator.comparing(AnimalBO::getWeight))
				.orElse(null);
	}
}
