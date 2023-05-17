package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.map.util.CardinalPoint;
import pawtropolis.game.util.Descriptor;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GoCommand extends ParameterizedCommand {

    protected GoCommand(GameSessionBO gameSessionBO) {
        super(gameSessionBO);
    }

    @Override
    public void execute() {
        CardinalPoint direction = CardinalPoint.of(parameter);
        if (direction == null) {
            log.info("\nUnrecognized direction\nWhere do you want to go? "
                    + Arrays.stream(CardinalPoint.values())
                    .map(c -> c.getName() + " - ")
                    .collect(Collectors.joining()) + "\n");
            return;
        }
        RoomBO currentRoom = gameSessionBO.getCurrentRoom();
        DoorBO doorBO = currentRoom.getDoor(direction);
        if (doorBO == null) {
            log.info("\nNothing to show in this direction!\n");
            return;
        }
        RoomBO adjacentRoom = doorBO.open();
        if (adjacentRoom != null) {
            gameSessionBO.setCurrentRoom(adjacentRoom);
            log.info(Descriptor.getRoomDescription(adjacentRoom));
        }
    }
}
