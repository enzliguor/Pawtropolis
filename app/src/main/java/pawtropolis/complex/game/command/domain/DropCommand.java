package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.domain.ItemBO;
import pawtropolis.complex.game.domain.PlayerBO;
import pawtropolis.complex.game.map.domain.RoomBO;

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
