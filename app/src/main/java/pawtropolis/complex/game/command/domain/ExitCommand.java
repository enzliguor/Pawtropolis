package pawtropolis.complex.game.command.domain;

import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;

@Component
public class ExitCommand extends Command {
    protected ExitCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        this.gameController.endGame();
    }
}
