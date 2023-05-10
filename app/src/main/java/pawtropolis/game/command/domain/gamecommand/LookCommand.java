package pawtropolis.game.command.domain.gamecommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.util.Descriptor;

@Slf4j
@Component
public class LookCommand extends GameCommand {

    protected LookCommand(GameSessionBO gameSessionBO) {
        super(gameSessionBO);
    }

    @Override
    public void execute() {
        RoomBO currentRoom = this.gameSessionBO.getCurrentRoom();
        String roomDescription = Descriptor.getRoomDescription(currentRoom);

        log.info(roomDescription);

    }
}
