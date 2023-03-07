package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.domain.Player;

@Slf4j
@Component("drop")
public class DropCommand extends ParameterizedCommand{

    protected DropCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        Player player = this.gameController.getPlayer();
        Item item = player.removeItemByName(parameter);
        if (item == null) {
            log.info("Item not found");
        } else {
            this.gameController.getCurrentRoom().addItem(item);
        }
    }
}
