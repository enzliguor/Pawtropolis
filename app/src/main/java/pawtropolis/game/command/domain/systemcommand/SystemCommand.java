package pawtropolis.game.command.domain.systemcommand;

import pawtropolis.game.GameController;
import pawtropolis.game.command.domain.Command;

public abstract class SystemCommand implements Command {

    protected GameController gameController;

    protected SystemCommand(GameController gameController) {
        this.gameController = gameController;
    }
}
