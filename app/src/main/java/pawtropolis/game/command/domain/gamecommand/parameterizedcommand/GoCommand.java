package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.console.InputController;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.map.util.CardinalPoint;
import pawtropolis.game.util.Descriptor;
import pawtropolis.game.util.DoorUnlocker;

import java.util.Arrays;
import java.util.Set;
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
        if(doorBO.isLocked() && askToUnlock()){
            DoorUnlocker.tryToUnlock(doorBO, this.gameSessionBO.getPlayer());
        }
        RoomBO adjacentRoom = doorBO.open(currentRoom);
        if (adjacentRoom != null) {
            gameSessionBO.setCurrentRoom(adjacentRoom);
            log.info(Descriptor.getRoomDescription(adjacentRoom));
        }
    }
    private static boolean askToUnlock() {
        String input = InputController.readChoice("The door is locked: would you like to use an item to unlock it?", Set.of("Y", "N"));
        return input.equals("Y");
    }
}
