package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import pawtropolis.game.command.domain.gamecommand.GameCommand;
import pawtropolis.game.domain.SaveBlockBO;

public abstract class ParameterizedCommand extends GameCommand {

    protected String parameter;

    protected ParameterizedCommand(SaveBlockBO saveBlockBO) {
        super(saveBlockBO);
    }

    public void setParameter(String parameter){
        this.parameter = parameter;
    }
}
