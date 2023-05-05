package pawtropolis.game.command.domain.systemcommand;

import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;

@Component
public class ExitCommand extends SystemCommand {

    public ExitCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        this.gameController.endGame();
    }
}
