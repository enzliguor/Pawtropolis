package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.map.domain.Room;
import pawtropolis.complex.map.util.CardinalPoint;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component("go")
public class GoCommand extends ParameterizedCommand{
    public GoCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        CardinalPoint direction = CardinalPoint.of(parameter);
        if(direction == null){
            log.info("Unrecognized direction\nWhere do you want to go? "
                    + Arrays.stream(CardinalPoint.values())
                    .map(c ->c.getName() +" - ")
                    .collect(Collectors.joining())+ "\n");
            return;
        }
        Room currentRoom = this.gameController.getCurrentRoom();
        Room adjacentRoom = currentRoom.getAdjacentRoom(direction);
        if (adjacentRoom == null) {
            log.info("Nothing to show in this direction!\n");
        } else {
            this.gameController.setCurrentRoom(adjacentRoom);
            adjacentRoom.getRoomDescription();
        }
    }
}
