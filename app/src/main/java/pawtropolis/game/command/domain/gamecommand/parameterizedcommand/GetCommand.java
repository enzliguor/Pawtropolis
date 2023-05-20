package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;
import pawtropolis.game.domain.SaveBlockBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.util.Descriptor;

@Component
public class GetCommand extends ParameterizedCommand {

    protected GetCommand(SaveBlockBO saveBlockBO) {
        super(saveBlockBO);
    }

    @Override
    public void execute() {
        RoomBO currentRoom = this.saveBlockBO.getCurrentRoom();
        PlayerBO player = this.saveBlockBO.getPlayer();
        ItemBO item = currentRoom.findItemByName(parameter);
        if (item == null) {
            CustomLogger.error("\nItem not found\n");
        }else if(player.checkItemFitsInBag(item)){
            player.collectItem(item);
            currentRoom.removeItem(item);
            CustomLogger.gameMessage(Descriptor.getBagDescription(player));
        }else{
            CustomLogger.error("\nYour BagBO is too full! \n" +
                    "Free up " +
                    (item.getSlotsRequired() - player.getAvailableSlot()) +
                    " slots to get this item\n");
        }
    }
}
