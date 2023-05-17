package pawtropolis.game.command.domain;


import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;

@Component
public class WrongCommand implements Command {

    private static final String WRONG_COMMAND =  """
				
				Unrecognized gameCommand
				Type 'help' for a list of available gameCommand
				""";

    @Override
    public void execute() {
        CustomLogger.error(WRONG_COMMAND);
    }
}
