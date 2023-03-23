package pawtropolis.complex.game.command.domain;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;

@Slf4j
@Component
public class WrongCommand extends Command {

    private static final String WRONG_COMMAND =  """
				
				Unrecognized command
				Type 'help' for a list of available command
				""";

    protected WrongCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        log.info(WRONG_COMMAND);
    }
}
