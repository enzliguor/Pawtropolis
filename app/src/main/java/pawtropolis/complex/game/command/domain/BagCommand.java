package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.service.GameService;

@Component("bag")
public class BagCommand extends Command{
    @Autowired
    protected BagCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {
        this.gameService.showBagContent();
    }
}
