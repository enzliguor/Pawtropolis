package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.domain.Player;
import pawtropolis.complex.game.map.domain.Room;

@Slf4j
@Component
public class DropCommand extends ParameterizedCommand {

    protected DropCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        Player player = this.gameController.getPlayer();
        Item item = player.dropItemByName(parameter);
        if (item == null) {
            log.info("\nItem not found");
        } else {
            Room currentRoom = gameController.getCurrentRoom();
            currentRoom.addItem(item);
        }
    }
}
