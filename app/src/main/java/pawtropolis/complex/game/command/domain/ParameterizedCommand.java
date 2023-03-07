package pawtropolis.complex.game.command.domain;

import pawtropolis.complex.game.GameController;

public abstract class ParameterizedCommand extends Command {

    protected String parameter;

    protected ParameterizedCommand(GameController gameController){
        super(gameController);
    }

    @Override
    public abstract void execute();

    public void setParameter(String parameter){
        this.parameter = parameter;
    }
}
