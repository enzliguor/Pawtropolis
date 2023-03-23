package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.complex.game.GameController;

public abstract class Command {

    protected GameController gameController;

    @Autowired
    protected Command(GameController gameController){
        this.gameController = gameController;
    }

    public abstract void execute();

}
