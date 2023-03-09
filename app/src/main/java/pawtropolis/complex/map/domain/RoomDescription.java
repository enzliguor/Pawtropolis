package pawtropolis.complex.map.domain;

import lombok.*;
import pawtropolis.complex.animals.domain.Animal;
import pawtropolis.complex.game.domain.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@EqualsAndHashCode
public class RoomDescription {
    @NonNull
    @Getter
    @Setter
    private String name;
    private final Map<String, Item> items = new HashMap<>();
    private final Map<Class<? extends Animal>, List<Animal>> animals = new HashMap<>();

    public Item findItemByName(String nameItem) {
        return this.items.get(nameItem);
    }

    public Map<Class<? extends Animal>, List<Animal>> getAnimals(){
        return Map.copyOf(this.animals);
    }

    public Map<String, Item> getItems(){
        return Map.copyOf(this.items);
    }

    public boolean removeItem(Item item) {
        Item itemTemp = this.items.remove(item.getName());
        return itemTemp != null;
    }

    public boolean addItem(Item item) {
        if(item != null){
            this.items.put(item.getName(), item);
            return true;
        }
        return false;
    }

    public void addAllItems(List<Item> items) {
        items.forEach(this::addItem);
    }

    public void addAnimal(Animal animal) {
        this.animals.computeIfAbsent(animal.getClass(), k-> new ArrayList<>()).add(animal);
    }

    public void addAllAnimals(List<Animal> animals) {
        animals.forEach(this::addAnimal);
    }

    public boolean removeAnimal(Animal animal) {
        return animals.get(animal.getClass()).removeIf(a -> a.equals(animal));
    }

}
