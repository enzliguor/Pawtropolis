package pawtropolis.complex.game.command.domain;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.service.GameService;

@Slf4j
@Component
public class WrongCommand extends GameCommand {

    private static final String WRONG_COMMAND =  """
				Unrecognized command
				Type 'help' for a list of available command
				""";
    @Autowired
    protected WrongCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {
        log.info(WRONG_COMMAND);
    }
}
