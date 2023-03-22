package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.map.maploader.MapInitializer;


public abstract class SystemCommand extends Command {

    protected GameController gameController;

    @Autowired
    protected SystemCommand(MapInitializer mapInitializer, GameController gameController) {
        super(mapInitializer);
        this.gameController = gameController;
    }
}
