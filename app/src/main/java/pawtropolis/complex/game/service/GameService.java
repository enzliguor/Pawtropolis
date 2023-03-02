package pawtropolis.complex.game.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.domain.Player;
import pawtropolis.complex.map.domain.Room;
import pawtropolis.complex.map.util.CardinalPoint;
import pawtropolis.complex.map.util.MapInitializer;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameService {

    private final Player player;
    private Room currentRoom ;

    private GameService(Player player, Room currentRoom) {
        this.player = new Player();
        this.currentRoom = MapInitializer.populateMap();
    }

    public void goToAnAdjacentRoom(String cardinalPoint) {
        CardinalPoint direction = CardinalPoint.of(cardinalPoint);
        if(direction == null){
            log.info("Unrecognized direction\nWhere do you want to go? "
                    + Arrays.stream(CardinalPoint.values())
                    .map(c ->c.getName() +" - ")
                    .collect(Collectors.joining())+ "\n");
            return;
        }
        Room adjacentRoom = this.currentRoom.getAdjacentRoom(direction);
        if (adjacentRoom == null) {
            log.info("Nothing to show in this direction!\n");
        } else {
            this.currentRoom = adjacentRoom;
            this.look();
        }
    }

    public void look() {
        this.currentRoom.getRoomDescription();
    }

    public void showBagContent() {
        this.player.showBagContent();
    }

    public void collectItemByName(String itemName) {
        Item item = this.currentRoom.findItemByName(itemName);
        if (item == null) {
            log.info("Item not found\n");
        } else {
            if (this.player.addItem(item)) {
                this.currentRoom.removeItem(item);
            }

        }
    }

    public void dropItemByName(String itemName) {
        Item item = this.player.removeItemByName(itemName);
        if (item == null) {
            log.info("Item not found");
        } else {
            this.currentRoom.addItem(item);
        }
    }

    public void setPlayerName(String name){
        this.player.setName(name);
    }

}
