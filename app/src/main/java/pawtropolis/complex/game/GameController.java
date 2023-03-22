package pawtropolis.complex.game;


import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.command.CommandManager;
import pawtropolis.complex.console.InputController;

@Slf4j
@ToString
@Component
public class GameController implements ApplicationRunner {
    @Getter
    private boolean gameRunning;

    private final CommandManager commandManager;

    @Autowired
    private GameController(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.gameRunning = false;
    }
    public void runGame() {
        log.info("Type HELP for a list of available command");

        while (gameRunning) {
            log.info("Where are you going to go?>");

            commandManager.execute(InputController.readString());
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
