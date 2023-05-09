package pawtropolis.game.command.domain.gamecommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.persistence.service.GameSessionService;
import pawtropolis.persistence.service.PlayerService;
import pawtropolis.persistence.service.RoomService;

@Component
public class SaveCommand extends GameCommand {

    private final GameSessionService gameSessionService;
    private final PlayerService playerService;
    private final RoomService roomService;

    @Autowired
    protected SaveCommand(GameSessionBO gameSessionBO, GameSessionService gameSessionService, PlayerService playerService, RoomService roomService) {
        super(gameSessionBO);
        this.gameSessionService = gameSessionService;
        this.playerService = playerService;
        this.roomService = roomService;
    }


    @Override
    public void execute() {
        if(this.gameSessionBO.isNotPersisted()){
            PlayerBO playerBO = this.playerService.saveOrUpdate(this.gameSessionBO.getPlayer());
            RoomBO roomBO = this.roomService.saveOrUpdate(this.gameSessionBO.getCurrentRoom());

            playerBO.setBag(this.gameSessionBO.getPlayer().getBag());
            this.gameSessionBO.setPlayer(playerBO);
            this.gameSessionBO.setCurrentRoom(roomBO);
        }
        GameSessionBO savedGameSession = this.gameSessionService.saveOrUpdate(this.gameSessionBO);

        if(this.gameSessionBO.isNotPersisted()){
            this.gameSessionBO.initializeGameSession(savedGameSession);
        }
    }
}
