package pawtropolis.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelpCommand implements Command {

    private static final String HELP = """
				
				look             -> get a description of the current Room
				bag              -> show the content of your bag
				get 'item name'  -> collect an item from the room
				drop 'item name' -> drop an item from your bag
				go 'direction'   -> move into another adjacent room, if available (north, south, east, west)
				start            -> pause/restart
				exit             -> close the game
				""";

    @Override
    public void execute() {
        log.info(HELP);
    }
}
