package pawtropolis.game.command.domain.gamecommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.persistence.service.GameSessionService;
import pawtropolis.persistence.service.PlayerService;

@Component
public class SaveCommand extends GameCommand {

    private final GameSessionService gameSessionService;
    private final PlayerService playerService;

    @Autowired
    protected SaveCommand(GameSessionBO gameSessionBO, GameSessionService gameSessionService, PlayerService playerService) {
        super(gameSessionBO);
        this.gameSessionService = gameSessionService;
        this.playerService = playerService;
    }


    @Override
    public void execute() {
        if(this.gameSessionBO.isNotPersisted()){
            PlayerBO playerBO = this.playerService.saveOrUpdate(this.gameSessionBO.getPlayer());
            playerBO.setBag(this.gameSessionBO.getPlayer().getBag());
            this.gameSessionBO.setPlayer(playerBO);
        }
        GameSessionBO savedGameSession = this.gameSessionService.saveOrUpdate(this.gameSessionBO);

        if(this.gameSessionBO.isNotPersisted()){
            this.gameSessionBO.initializeGameSession(savedGameSession);
        }
    }
}
