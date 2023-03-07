package pawtropolis.complex.game.command.domain;

import pawtropolis.complex.game.GameController;

public abstract class Command {

    protected GameController gameController;

    protected Command(GameController gameController){
        this.gameController = gameController;
    }

    public abstract void execute();
}
