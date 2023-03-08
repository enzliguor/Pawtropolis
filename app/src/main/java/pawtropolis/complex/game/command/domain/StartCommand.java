package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.console.InputController;
import pawtropolis.complex.game.service.GameService;

@Component("start")
@Slf4j
public class StartCommand extends Command {
    @Autowired
    protected StartCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {
        log.info("Type player name:");
        String playerName = InputController.readString();
        this.gameService.setPlayerName(playerName);
    }
}
