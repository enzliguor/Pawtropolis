package pawtropolis.game.command.domain.gamecommand.systemcommand;

import pawtropolis.game.GameController;
import pawtropolis.game.command.domain.gamecommand.GameCommand;
import pawtropolis.game.domain.GameSessionBO;

public abstract class SystemCommand extends GameCommand {

    protected GameController gameController;

    protected SystemCommand(GameController gameController, GameSessionBO gameSessionBO) {
        super(gameSessionBO);
        this.gameController = gameController;
    }
}
