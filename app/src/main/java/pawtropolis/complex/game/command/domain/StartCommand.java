package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.console.InputController;
import pawtropolis.complex.game.service.GameService;

@Component("start")
@Slf4j
public class StartCommand extends GameCommand {

    @Value("${Player.DEFAULT_LIFE_POINTS}")
    private int defaultLifePoints;

    @Value("${Bag.CAPACITY}")
    private int bagCapacity;
    @Autowired
    protected StartCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {
        this.gameService.setPlayerLifePoints(defaultLifePoints);

        this.gameService.setPlayerBagCapacity(bagCapacity);

        log.info("Type player name:");
        String playerName = InputController.readString();
        this.gameService.setPlayerName(playerName);

    }
}
