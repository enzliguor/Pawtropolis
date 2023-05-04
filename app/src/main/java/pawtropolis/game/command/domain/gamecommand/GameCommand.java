package pawtropolis.game.command.domain.gamecommand;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.game.command.domain.Command;
import pawtropolis.game.domain.GameSessionBO;

public abstract class GameCommand implements Command {

    protected GameSessionBO gameSessionBO;

    @Autowired
    protected GameCommand(GameSessionBO gameSessionBO){
        this.gameSessionBO = gameSessionBO;
    }

}
