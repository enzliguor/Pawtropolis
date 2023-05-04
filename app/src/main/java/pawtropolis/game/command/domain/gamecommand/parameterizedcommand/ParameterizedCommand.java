package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import pawtropolis.game.command.domain.gamecommand.GameCommand;
import pawtropolis.game.domain.GameSessionBO;

public abstract class ParameterizedCommand extends GameCommand {

    protected String parameter;

    protected ParameterizedCommand(GameSessionBO gameSessionBO) {
        super(gameSessionBO);
    }

    public void setParameter(String parameter){
        this.parameter = parameter;
    }
}
