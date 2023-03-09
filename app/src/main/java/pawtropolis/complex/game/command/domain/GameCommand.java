package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.complex.game.service.GameService;

public abstract class GameCommand implements Command {

    protected GameService gameService;

    @Autowired
    protected GameCommand(GameService gameService){
        this.gameService = gameService;
    }

}
