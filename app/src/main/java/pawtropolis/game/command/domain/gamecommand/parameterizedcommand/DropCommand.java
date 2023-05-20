package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;
import pawtropolis.game.domain.SaveBlockBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.util.Descriptor;

@Component
public class DropCommand extends ParameterizedCommand {

    protected DropCommand(SaveBlockBO saveBlockBO) {
        super(saveBlockBO);
    }

    @Override
    public void execute() {
        PlayerBO player = this.saveBlockBO.getPlayer();
        ItemBO item = player.removeItemByName(parameter);
        if (item == null) {
            CustomLogger.error("\nItem not found");
        } else {
            RoomBO currentRoom = this.saveBlockBO.getCurrentRoom();
            currentRoom.addItem(item);
            CustomLogger.gameMessage(Descriptor.getBagDescription(player));
        }
    }
}
