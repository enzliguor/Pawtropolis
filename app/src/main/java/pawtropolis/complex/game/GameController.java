package pawtropolis.complex.game;


import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.command.CommandManager;
import pawtropolis.complex.game.command.domain.Command;
import pawtropolis.complex.game.console.InputController;

@Slf4j
@ToString
@Component
public class GameController implements ApplicationRunner {

    private boolean gameEnded;

    private final CommandManager commandManager;

    @Autowired
    private GameController(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.gameEnded = false;
    }

    public void runGame() {
        Command command = commandManager.getCommand("start");
        command.execute();

        log.info("Hello Player!\n");
        command = commandManager.getCommand("look");
        command.execute();

        log.info("Type help for a list of available command\n");

        while (!gameEnded) {
            log.info("Where are you going to go?");
            log.info(">");

            command = commandManager.getCommand(InputController.readString());
            command.execute();
        }
    }
    public void endGame() {
        this.gameEnded = true;
    }
    @Override
    public void run(ApplicationArguments args){
        runGame();
    }
}
