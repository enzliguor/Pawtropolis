package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.service.GameService;

@Component("exit")
public class ExitCommand extends SystemCommand {
    @Autowired
    protected ExitCommand(GameService gameService, GameController gameController) {
        super(gameService, gameController);
    }

    @Override
    public void execute() {
        this.gameController.endGame();
    }
}
