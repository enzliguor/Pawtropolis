package pawtropolis.game.command.domain.gamecommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.util.Descriptor;

@Component
@Slf4j
public class BagCommand extends GameCommand {

    protected BagCommand(GameSessionBO gameSessionBO) {
        super(gameSessionBO);
    }

    @Override
    public void execute() {
        PlayerBO playerBO = this.gameSessionBO.getPlayer();
        String bagDescription = Descriptor.getBagDescription(playerBO);
        log.info(bagDescription);
    }
}
