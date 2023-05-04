package pawtropolis.game.command.domain;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WrongCommand implements Command {

    private static final String WRONG_COMMAND =  """
				
				Unrecognized gameCommand
				Type 'help' for a list of available gameCommand
				""";

    @Override
    public void execute() {
        log.info(WRONG_COMMAND);
    }
}
