package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;

@Component("exit")
public class ExitCommand extends SystemCommand {

   @Autowired
    protected ExitCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        this.gameController.endGame();
    }
}
