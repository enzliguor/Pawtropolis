package pawtropolis.complex.game;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.command.CommandManager;
import pawtropolis.complex.console.InputController;
import pawtropolis.complex.game.domain.Player;
import pawtropolis.complex.game.map.domain.Room;
import pawtropolis.complex.game.map.initializer.MapInitializer;

@Slf4j
@ToString
@Component
public class GameController implements ApplicationRunner {
    @Getter
    private Player player;
    @Getter
    @Setter
    private Room currentRoom;
    @Getter
    private boolean gameRunning;
    private final CommandManager commandManager;
    @Value("${Player.DEFAULT_LIFE_POINTS}")
    private int defaultLifePoints;
    @Value("${Bag.CAPACITY}")
    private int bagCapacity;

    @Autowired
    private GameController(MapInitializer mapInitializer, CommandManager commandManager) {
        this.gameRunning = false;
        this.currentRoom = mapInitializer.populateMap();
        this.commandManager = commandManager;
    }
    public void runGame() {
        this.gameRunning = true;
        log.info("\nType player name:");
        String playerName = InputController.readString();
        this.player = new Player(playerName, defaultLifePoints);
        this.player.setBagCapacity(bagCapacity);

        log.info("\nHello Player!\n");
        log.info("Type HELP for a list of available command");

        while (gameRunning) {
            log.info("Where are you going to go?>");

            commandManager.executeCommand(InputController.readString());
        }
    }
    public void endGame() {
        this.gameRunning = false;
    }

    @Override
    public void run(ApplicationArguments args){
        runGame();
    }
}
