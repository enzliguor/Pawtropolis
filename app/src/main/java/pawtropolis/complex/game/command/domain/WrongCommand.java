package pawtropolis.complex.game.command.domain;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.map.maploader.MapInitializer;

@Slf4j
@Component
public class WrongCommand extends Command {

    private static final String WRONG_COMMAND =  """
				
				Unrecognized command
				Type 'help' for a list of available command
				""";

    protected WrongCommand(MapInitializer mapInitializer) {
        super(mapInitializer);
    }

    @Override
    public void execute() {
        log.info(WRONG_COMMAND);
    }
}
