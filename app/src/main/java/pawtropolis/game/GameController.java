package pawtropolis.game;


import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.console.InputController;
import pawtropolis.game.command.CommandManager;

import java.util.Set;

@Slf4j
@ToString
@Component
public class GameController {
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
        commandManager.executeCommand("look");

        while (gameRunning) {
            log.info("\n>Where are you going to go?");

            commandManager.executeCommand(InputController.readString());
        }
    }
    public void endGame() {
        this.gameRunning = false;
        String choice = InputController.readChoice("Do you want to save before exit?", Set.of("YES","NO"));
        if (choice.equals("YES")) commandManager.executeCommand("save");
    }

    public void pause(){
        InputController.evaluateInput("""
                     PAUSE
            Type 'start' to continue""", input -> input.equals("start"), "");
    }
}
