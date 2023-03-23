package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.domain.Player;
import pawtropolis.complex.game.map.domain.Room;

@Slf4j
@Component
public class GetCommand extends ParameterizedCommand {

    protected GetCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        Room currentRoom = this.gameController.getCurrentRoom();
        Player player = this.gameController.getPlayer();
        Item item = currentRoom.findItemByName(parameter);
        if (item == null) {
            log.info("\nItem not found\n");
        }else if(player.checkItemFitsInBag(item)){
            player.collectItem(item);
            currentRoom.removeItem(item);
        }else{
            log.info("\nYour Bag is too full! \n" +
                    "Free up " +
                    (item.getSlotRequired() - player.getAvailableSlot()) +
                    " slots to get this item\n");
        }
    }
}
