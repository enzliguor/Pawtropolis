package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.complex.game.service.GameService;

public abstract class ParameterizedGameCommand extends GameCommand {

    protected String parameter;

    @Autowired
    protected ParameterizedGameCommand(GameService gameService) {
        super(gameService);
    }

    public void setParameter(String parameter){
        this.parameter = parameter;
    }
}
