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
import pawtropolis.complex.game.map.mapinitializer.MapInitializer;

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

    @Autowired
    private GameController(MapInitializer mapInitializer, CommandManager commandManager) {
        this.gameRunning = true;
        this.currentRoom = mapInitializer.populateMap();
        this.commandManager = commandManager;
        this.gameRunning = false;
    }
    public void runGame() {
        log.info("Type HELP for a list of available command");

        while (gameRunning) {
            log.info("Where are you going to go?>");

            commandManager.executeCommand(InputController.readString());
        }
    }
    public void endGame() {
        this.gameRunning = false;
    }
    public void startGame(){
        this.gameRunning = true;
        runGame();
    }
    @Override
    public void run(ApplicationArguments args){
        commandManager.execute("start");
    }
}
