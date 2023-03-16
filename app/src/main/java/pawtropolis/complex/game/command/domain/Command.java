package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.complex.game.service.GameService;

public abstract class Command {

    protected GameService gameService;

    @Autowired
    protected Command(GameService gameService){
        this.gameService = gameService;
    }

    public abstract void execute();

}
