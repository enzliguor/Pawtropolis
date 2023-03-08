package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.service.GameService;

@Component("look")
public class LookCommand extends Command {
    @Autowired
    protected LookCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {
        this.gameService.look();
    }
}
