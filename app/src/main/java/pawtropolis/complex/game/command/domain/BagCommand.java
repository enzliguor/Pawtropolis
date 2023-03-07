package pawtropolis.complex.game.command.domain;

import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;

@Component("bag")
public class BagCommand extends Command{

    protected BagCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        this.gameController.getPlayer().showBagContent();
    }
}
