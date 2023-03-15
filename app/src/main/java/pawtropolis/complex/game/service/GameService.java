package pawtropolis.complex.game.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.complex.animals.domain.Animal;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.domain.Player;
import pawtropolis.complex.map.domain.Room;
import pawtropolis.complex.map.maploader.MapInitializer;
import pawtropolis.complex.map.util.CardinalPoint;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameService {

    private final Player player;
    private Room currentRoom ;

    @Autowired
    private GameService(MapInitializer mapInitializer) {
        this.player = new Player();
        this.currentRoom = mapInitializer.populateMap();
    }

    public Room getAdjacentRoom(CardinalPoint cardinalPoint){
        return this.currentRoom.getAdjacentRoom(cardinalPoint);
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    public Item findItemInRoomByName(String itemName){
        List<Item> items = this.currentRoom.getItems();
        return items.stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElse(null);
    }

    public void collectItem(Item item){
        this.player.addItem(item);
    }

    public boolean checkItemFitsInPlayerBag(Item item){
        return item.getSlotRequired() < getAvailableSlot();
    }

    public void removeItemFromRoom(Item item){
        this.currentRoom.removeItem(item);
    }

    public Item dropItemByName(String itemName){
        List<Item> items = this.player.getBagContent();
        Item item = items.stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElse(null);
        return this.player.removeItem(item);

    }

    public void addItemInRoom(Item item){
        if (item!=null){
            this.currentRoom.addItem(item);
        }
    }

   public List<String> getRoomItemsName(){
        List<Item> items = this.currentRoom.getItems();
        return items.stream()
                .map(Item::getName)
                .toList();
   }

   public Map<Class<? extends Animal>, List<String>> getRoomAnimalsName(){
        List<Animal> animals = this.currentRoom.getAnimals();
       return  animals.stream()
               .collect(Collectors.groupingBy(
                       Animal::getClass,
                       Collectors.mapping(Animal::getName, Collectors.toList())
               ));
   }

   public String getRoomName(){
        return this.currentRoom.getName();
   }

    public List<String> getPlayerBagContent(){
        List<Item> items = this.player.getBagContent();
        return items.stream()
                .map(Item::getName)
                .toList();
    }

    public int getAvailableSlot(){
        return this.player.getAvailableSlot();
    }

    public void setPlayerName(String name){
        this.player.setName(name);
    }

    public void setPlayerLifePoints(int lifePoints){this.player.setLifePoints(lifePoints);}

    public void setPlayerBagCapacity(int bagCapacity){
        this.player.setBagCapacity(bagCapacity);
    }
}
