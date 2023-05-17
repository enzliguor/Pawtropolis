package pawtropolis.game.command.domain.systemcommand;

import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;

@Component
public class StartCommand extends SystemCommand {
    protected StartCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        if (this.gameController.isGameRunning()) {
            this.gameController.pause();
        }
    }

}
