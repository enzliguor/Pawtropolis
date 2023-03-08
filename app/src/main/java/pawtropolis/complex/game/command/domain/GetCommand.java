package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.service.GameService;

@Slf4j
@Component("get")
public class GetCommand extends ParameterizedCommand{
    @Autowired
    protected GetCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {
        Item item = gameService.findItemInRoomByName(parameter);
        if (item == null) {
            log.info("Item not found\n");
        } else {
            if (this.gameService.collectItem(item)) {
                this.gameService.removeItemFromRoom(item);
            }

        }
    }
}
