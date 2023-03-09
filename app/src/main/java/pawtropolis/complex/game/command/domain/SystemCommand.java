package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.complex.game.GameController;


public abstract class SystemCommand implements Command{

    protected GameController gameController;

    @Autowired
    protected SystemCommand(GameController gameController){
        this.gameController = gameController;
    }
}
