package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.service.GameService;

@Slf4j
@Component("help")
public class HelpCommand extends Command {

    private static final String HELP = """
				
				look             -> get a description of the current Room
				bag              -> show the content of your bag
				get 'item name'  -> collect an item from the room
				drop 'item name' -> drop an item from your bag
				go 'direction'   -> move into another adjacent room, if available (north, south, east, west)
				exit             -> close the game
				""";
    @Autowired
    protected HelpCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {
        log.info(HELP);
    }
}
