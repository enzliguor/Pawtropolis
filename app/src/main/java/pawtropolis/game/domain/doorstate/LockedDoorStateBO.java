package pawtropolis.game.domain.doorstate;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.console.InputController;
import pawtropolis.game.domain.DoorBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.util.GameUtility;

import java.util.List;
import java.util.Set;


@Slf4j
@SuperBuilder
public class LockedDoorStateBO extends DoorStateBO{
    @Getter
    private final ItemBO key;

    protected LockedDoorStateBO(DoorBO doorBO, ItemBO key) {
        super(doorBO);
        this.key = key;
    }
    @Override
    public RoomBO open() {
        if(askToUnlock()){
            PlayerBO playerBO = GameUtility.getCurrentPlayer();
            ItemBO itemKey = chooseItem(playerBO);
            if (itemKey == null) {
                return null;
            }
            if (!tryToUnlock(itemKey)) {
                log.info("\nThis is not the right item");
                return null;
            }
            log.info("\nYou unlocked the door!");
            playerBO.removeItemByName(itemKey.getName());
            return this.doorBO.open();
        }
        return null;
    }

    public boolean tryToUnlock(ItemBO itemKey) {
        if(this.key.equals(itemKey)){
            this.doorBO.setState(UnlockedDoorStateBO.builder()
                    .id(1L)
                    .doorBO(this.doorBO)
                    .build());
        }
        return !this.doorBO.isLocked();
    }

    private boolean askToUnlock() {
        String message = "The door is locked: would you like to use an item to unlock it?";
        String input = InputController.readChoice(message, List.of("Y", "N"));
        return input.equals("Y");
    }

    private ItemBO chooseItem(PlayerBO playerBO) {
        Set<String> itemsName = playerBO.getBagContent().keySet();
        if (itemsName.isEmpty()) {
            log.info("\nYou don't have any item!\nGo pick up some items!");
            return null;
        }
        String input = InputController.readChoice("Type the name of the chosen item", itemsName);
        return playerBO.getItemByName(input);
    }
}
