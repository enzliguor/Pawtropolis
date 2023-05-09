package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.console.InputController;
import pawtropolis.game.command.domain.gamecommand.LookCommand;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.LockedRoomBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.map.util.CardinalPoint;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GoCommand extends ParameterizedCommand {

    private final LookCommand lookCommand;

    protected GoCommand(GameSessionBO gameSessionBO, LookCommand lookCommand) {
        super(gameSessionBO);
        this.lookCommand = lookCommand;
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
        RoomBO adjacentRoom = currentRoom.getAdjacentRoom(direction);
        if (adjacentRoom == null) {
            log.info("\nNothing to show in this direction!\n");
            return;
        }
        if (adjacentRoom instanceof LockedRoomBO lockedRoomBO) {
            adjacentRoom = (askToUnlock()) ? tryToUnlock(lockedRoomBO) : null;
        }
        if (adjacentRoom != null) {
            gameSessionBO.setCurrentRoom(adjacentRoom);
            this.lookCommand.execute();
        }
    }

    private boolean askToUnlock(){
        String input = InputController.readChoice("The door is locked: would you like to use an item to unlock it?", List.of("Y", "N"));
        return input.equals("Y");
    }

    private RoomBO tryToUnlock(LockedRoomBO lockedRoomBO) {
            ItemBO itemKey = chooseItem();
            if(itemKey==null){
                return null;
            }
            RoomBO roomBO = lockedRoomBO.tryToUnlock(itemKey);
            if (roomBO == null) {
                log.info("\nThis is not the right item");
            } else {
                log.info("\nYou unlocked the door!");
                this.gameSessionBO.getPlayer().dropItemByName(itemKey.getName());
            }
            return roomBO;
    }

    private ItemBO chooseItem() {
        Set<String> itemsName = this.gameSessionBO.getPlayer().getBagContent().keySet();
        if (itemsName.isEmpty()) {
            log.info("\nYou don't have any item!\nGo pick up some items!");
            return null;
        }
        String input = InputController.readChoice("Type the name of the chosen item", itemsName);
        return this.gameSessionBO.getPlayer().getItemByName(input);
    }
}
