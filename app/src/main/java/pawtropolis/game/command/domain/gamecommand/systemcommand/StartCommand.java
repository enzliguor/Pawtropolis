package pawtropolis.game.command.domain.gamecommand.systemcommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.game.domain.GameSessionBO;

@Component
@Slf4j
public class StartCommand extends SystemCommand {
    protected StartCommand(GameController gameController, GameSessionBO gameSessionBO) {
        super(gameController, gameSessionBO);
    }

    @Override
    public void execute() {
        if (this.gameController.isGameRunning()) {
            this.gameController.pause();
        }
    }

}
