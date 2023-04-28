package pawtropolis.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;

@Slf4j
@Component
public class DropCommand extends ParameterizedCommand {

    protected DropCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        PlayerBO player = this.gameController.getPlayer();
        ItemBO item = player.dropItemByName(parameter);
        if (item == null) {
            log.info("\nItemBO not found");
        } else {
            RoomBO currentRoom = gameController.getCurrentRoom();
            currentRoom.addItem(item);
        }
    }
}
