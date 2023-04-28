package pawtropolis.game;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pawtropolis.game.command.CommandManager;
import pawtropolis.console.InputController;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.map.initializer.MapInitializer;

@Slf4j
@ToString
@Component
public class GameController implements ApplicationRunner {
    @Getter
    private PlayerBO player;
    @Getter
    @Setter
    private RoomBO currentRoom;
    @Getter
    private boolean gameRunning;
    private final CommandManager commandManager;

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
        this.player = PlayerBO.builder().name(playerName).build();

        log.info("\nHello " + playerName + "!\n");
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
