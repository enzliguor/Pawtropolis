package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.command.domain.gamecommand.BagCommand;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;

@Slf4j
@Component
public class DropCommand extends ParameterizedCommand {

    private final BagCommand bagCommand;
    protected DropCommand(GameSessionBO gameSessionBO, BagCommand bagCommand) {
        super(gameSessionBO);
        this.bagCommand=bagCommand;
    }

    @Override
    public void execute() {
        PlayerBO player = this.gameSessionBO.getPlayer();
        ItemBO item = player.dropItemByName(parameter);
        if (item == null) {
            log.info("\nItemBO not found");
        } else {
            RoomBO currentRoom = this.gameSessionBO.getCurrentRoom();
            currentRoom.addItem(item);
            this.bagCommand.execute();
        }
    }
}
