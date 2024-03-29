package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;
import pawtropolis.console.InputController;
import pawtropolis.game.domain.door.DoorBO;
import pawtropolis.game.domain.SaveBlockBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.map.util.CardinalPoint;
import pawtropolis.game.util.Descriptor;
import pawtropolis.game.util.DoorUnlocker;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GoCommand extends ParameterizedCommand {

    protected GoCommand(SaveBlockBO saveBlockBO) {
        super(saveBlockBO);
    }

    @Override
    public void execute() {
        CardinalPoint direction = CardinalPoint.of(parameter);
        if (direction == null) {
            CustomLogger.error("\nUnrecognized direction\nWhere do you want to go? "
                    + Arrays.stream(CardinalPoint.values())
                    .map(c -> c.getName() + " - ")
                    .collect(Collectors.joining()) + "\n");
            return;
        }
        RoomBO currentRoom = saveBlockBO.getCurrentRoom();
        DoorBO doorBO = currentRoom.getDoor(direction);
        if (doorBO == null) {
            CustomLogger.error("\nNothing to show in this direction!\n");
            return;
        }
        if(doorBO.isLocked() && askToUnlock()){
            DoorUnlocker.tryToUnlock(doorBO, this.saveBlockBO.getPlayer());
        }
        RoomBO adjacentRoom = doorBO.open(currentRoom);
        if (adjacentRoom != null) {
            saveBlockBO.setCurrentRoom(adjacentRoom);
            CustomLogger.gameMessage(Descriptor.getRoomDescription(adjacentRoom));
        }
    }
    private static boolean askToUnlock() {
        String input = InputController.readChoice("The door is locked: would you like to use an item to unlock it?", Set.of("Y", "N"));
        return input.equals("Y");
    }
}
