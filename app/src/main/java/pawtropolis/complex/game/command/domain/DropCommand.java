package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.service.GameService;

@Slf4j
@Component("drop")
public class DropCommand extends ParameterizedCommand {
    @Autowired
    protected DropCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {
        Item item = gameService.dropItemByName(parameter);
        if (item == null) {
            log.info("\nItem not found");
        } else {
            this.gameService.addItemInRoom(item);
        }
    }
}
