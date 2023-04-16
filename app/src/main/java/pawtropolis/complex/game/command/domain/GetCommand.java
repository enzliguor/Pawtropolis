package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.domain.ItemBO;
import pawtropolis.complex.game.domain.PlayerBO;
import pawtropolis.complex.game.map.domain.RoomBO;

@Slf4j
@Component
public class GetCommand extends ParameterizedCommand {

    protected GetCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        RoomBO currentRoom = this.gameController.getCurrentRoom();
        PlayerBO player = this.gameController.getPlayer();
        ItemBO item = currentRoom.findItemByName(parameter);
        if (item == null) {
            log.info("\nItemBO not found\n");
        }else if(player.checkItemFitsInBag(item)){
            player.collectItem(item);
            currentRoom.removeItem(item);
        }else{
            log.info("\nYour BagBO is too full! \n" +
                    "Free up " +
                    (item.getSlotsRequired() - player.getAvailableSlot()) +
                    " slots to get this item\n");
        }
    }
}
