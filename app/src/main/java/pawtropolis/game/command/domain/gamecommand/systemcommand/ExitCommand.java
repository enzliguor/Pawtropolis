package pawtropolis.game.command.domain.gamecommand.systemcommand;

import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.game.domain.GameSessionBO;

@Component
public class ExitCommand extends SystemCommand {

    public ExitCommand(GameController gameController, GameSessionBO gameSessionBO) {
        super(gameController, gameSessionBO);
    }

    @Override
    public void execute() {
        this.gameController.endGame();
    }
}
