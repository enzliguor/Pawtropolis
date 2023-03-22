package pawtropolis.complex.game.command.domain;

import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.map.maploader.MapInitializer;

@Component("exit")
public class ExitCommand extends SystemCommand {

    protected ExitCommand(MapInitializer mapInitializer, GameController gameController) {
        super(mapInitializer, gameController);
    }

    @Override
    public void execute() {
        this.gameController.endGame();
    }
}
