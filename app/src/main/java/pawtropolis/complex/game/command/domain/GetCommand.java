package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.map.domain.Room;

@Slf4j
@Component("get")
public class GetCommand extends ParameterizedCommand{

    protected GetCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        Room currentRoom = this.gameController.getCurrentRoom();
        Item item = currentRoom.findItemByName(parameter);
        if (item == null) {
            log.info("Item not found\n");
        } else {
            if (this.gameController.getPlayer().addItem(item)) {
                currentRoom.removeItem(item);
            }

        }
    }
}
