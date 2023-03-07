package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;

@Component("look")
public class LookCommand extends Command {
    @Autowired
    protected LookCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        this.gameController.getCurrentRoom().getRoomDescription();
    }
}
