package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.service.GameService;


public abstract class SystemCommand extends Command {

    protected GameController gameController;

    @Autowired
    protected SystemCommand(GameService gameService, GameController gameController) {
        super(gameService);
        this.gameController = gameController;
    }
}
