package pawtropolis.complex.game.command.domain;

import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;

@Component("exit")
public class ExitGameCommand extends Command{
    protected ExitGameCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        this.gameController.exitGame();
    }
}
