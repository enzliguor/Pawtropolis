package pawtropolis.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.map.util.CardinalPoint;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GoCommand extends ParameterizedCommand {

    protected GoCommand(GameController gameController) {
        super(gameController);
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
        RoomBO currentRoom = gameController.getCurrentRoom();
        RoomBO adjacentRoom = currentRoom.getAdjacentRoom(direction);
        if (adjacentRoom == null) {
            log.info("\nNothing to show in this direction!\n");
        } else {
            gameController.setCurrentRoom(adjacentRoom);
        }
    }
}
