package pawtropolis.game;


import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pawtropolis.game.command.CommandManager;

@Slf4j
@ToString
@Component
public class GameController implements ApplicationRunner {
    @Getter
    private boolean gameRunning;
    private final CommandManager commandManager;

    @Autowired
    private GameController(CommandManager commandManager) {
        this.gameRunning = false;
        this.commandManager = commandManager;
    }
    public void runGame() {
        this.gameRunning = true;

        while (gameRunning) {
            log.info("\nWhere are you going to go?>");

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
