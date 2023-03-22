package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.console.InputController;
import pawtropolis.complex.game.map.maploader.MapInitializer;

@Component("start")
@Slf4j
public class StartCommand extends SystemCommand {

    @Value("${Player.DEFAULT_LIFE_POINTS}")
    private int defaultLifePoints;

    @Value("${Bag.CAPACITY}")
    private int bagCapacity;

    protected StartCommand(MapInitializer mapInitializer, GameController gameController) {
        super(mapInitializer, gameController);
    }

    @Override
    public void execute() {
        if(this.gameController.isGameRunning()){
            log.info("\nType EXIT to quit the game");
        }else{
            this.player.setLifePoints(defaultLifePoints);

            this.player.setBagCapacity(bagCapacity);

            log.info("\nType player name:");
            String playerName = InputController.readString();
            this.player.setName(playerName);

            this.gameController.startGame();
            log.info("\nHello Player!\n");
        }

    }
}
