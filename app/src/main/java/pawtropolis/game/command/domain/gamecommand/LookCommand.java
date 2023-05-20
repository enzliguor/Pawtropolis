package pawtropolis.game.command.domain.gamecommand;

import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;
import pawtropolis.game.domain.SaveBlockBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.util.Descriptor;

@Component
public class LookCommand extends GameCommand {

    protected LookCommand(SaveBlockBO saveBlockBO) {
        super(saveBlockBO);
    }

    @Override
    public void execute() {
        RoomBO currentRoom = this.saveBlockBO.getCurrentRoom();
        String roomDescription = Descriptor.getRoomDescription(currentRoom);

        CustomLogger.gameMessage(roomDescription);

    }
}
