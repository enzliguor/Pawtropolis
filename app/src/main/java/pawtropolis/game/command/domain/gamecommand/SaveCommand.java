package pawtropolis.game.command.domain.gamecommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.persistence.service.GameSessionService;

@Component
public class SaveCommand extends GameCommand {

    private final GameSessionService gameSessionService;

    @Autowired
    protected SaveCommand(GameSessionBO gameSessionBO, GameSessionService gameSessionService) {
        super(gameSessionBO);
        this.gameSessionService = gameSessionService;
    }

    @Override
    public void execute() {
        GameSessionBO savedGameSession = this.gameSessionService.saveOrUpdate(this.gameSessionBO);
        if(this.gameSessionBO.isNotPersisted()){
            this.gameSessionBO.initializeGameSession(savedGameSession);
        }
    }
}
