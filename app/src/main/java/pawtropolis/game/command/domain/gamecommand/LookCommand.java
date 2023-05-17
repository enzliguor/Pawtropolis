package pawtropolis.game.command.domain.gamecommand;

import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.util.Descriptor;

@Component
public class LookCommand extends GameCommand {

    protected LookCommand(GameSessionBO gameSessionBO) {
        super(gameSessionBO);
    }

    @Override
    public void execute() {
        RoomBO currentRoom = this.gameSessionBO.getCurrentRoom();
        String roomDescription = Descriptor.getRoomDescription(currentRoom);

        CustomLogger.gameMessage(roomDescription);

    }
}
