package pawtropolis.game.command.domain.gamecommand;

import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;
import pawtropolis.game.domain.SaveBlockBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.util.Descriptor;

@Component
public class BagCommand extends GameCommand {

    protected BagCommand(SaveBlockBO saveBlockBO) {
        super(saveBlockBO);
    }

    @Override
    public void execute() {
        PlayerBO playerBO = this.saveBlockBO.getPlayer();
        String bagDescription = Descriptor.getBagDescription(playerBO);
        CustomLogger.gameMessage(bagDescription);
    }
}
