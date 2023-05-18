package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.util.Descriptor;

@Slf4j
@Component
public class DropCommand extends ParameterizedCommand {

    protected DropCommand(GameSessionBO gameSessionBO) {
        super(gameSessionBO);
    }

    @Override
    public void execute() {
        PlayerBO player = this.gameSessionBO.getPlayer();
        ItemBO item = player.removeItemByName(parameter);
        if (item == null) {
            log.info("\nItemBO not found");
        } else {
            RoomBO currentRoom = this.gameSessionBO.getCurrentRoom();
            currentRoom.addItem(item);
            log.info(Descriptor.getBagDescription(player));
        }
    }
}
