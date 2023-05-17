package pawtropolis.game.command.domain.gamecommand;

import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.util.Descriptor;

@Component
public class BagCommand extends GameCommand {

    protected BagCommand(GameSessionBO gameSessionBO) {
        super(gameSessionBO);
    }

    @Override
    public void execute() {
        PlayerBO playerBO = this.gameSessionBO.getPlayer();
        String bagDescription = Descriptor.getBagDescription(playerBO);
        CustomLogger.gameMessage(bagDescription);
    }
}
