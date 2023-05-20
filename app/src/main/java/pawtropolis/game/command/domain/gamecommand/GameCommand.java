package pawtropolis.game.command.domain.gamecommand;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.game.command.domain.Command;
import pawtropolis.game.domain.SaveBlockBO;

public abstract class GameCommand implements Command {

    protected SaveBlockBO saveBlockBO;

    @Autowired
    protected GameCommand(SaveBlockBO saveBlockBO){
        this.saveBlockBO = saveBlockBO;
    }

}
