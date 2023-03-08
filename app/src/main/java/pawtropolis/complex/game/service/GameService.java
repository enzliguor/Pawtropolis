package pawtropolis.complex.game.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.domain.Player;
import pawtropolis.complex.map.domain.Room;
import pawtropolis.complex.map.maploader.MapInitializer;
import pawtropolis.complex.map.util.CardinalPoint;

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
        return this.currentRoom.findItemByName(itemName);
    }

    public boolean collectItem(Item item){
        return this.player.addItem(item);
    }

    public void removeItemFromRoom(Item item){
        this.currentRoom.removeItem(item);
    }

    public Item dropItemByName(String itemName){
        return this.player.removeItemByName(itemName);
    }

    public void addItemInRoom(Item item){
        this.currentRoom.addItem(item);
    }

    public void look() {
        this.currentRoom.getRoomDescription();
    }

    public void showBagContent() {
        this.player.showBagContent();
    }

    public void setPlayerName(String name){
        this.player.setName(name);
    }

}
