package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.service.GameService;
import pawtropolis.complex.game.map.domain.Room;
import pawtropolis.complex.game.map.util.CardinalPoint;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component("go")
public class GoCommand extends ParameterizedCommand {
    @Autowired
    protected GoCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {
        CardinalPoint direction = CardinalPoint.of(parameter);
        if(direction == null){
            log.info("\nUnrecognized direction\nWhere do you want to go? "
                    + Arrays.stream(CardinalPoint.values())
                    .map(c ->c.getName() +" - ")
                    .collect(Collectors.joining())+ "\n");
            return;
        }
        Room adjacentRoom = this.gameService.getAdjacentRoom(direction);
        if (adjacentRoom == null) {
            log.info("\nNothing to show in this direction!\n");
        } else {
            this.gameService.setCurrentRoom(adjacentRoom);
        }
    }
}
