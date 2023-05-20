package pawtropolis.game.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.console.InputController;
import pawtropolis.game.domain.door.DoorBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.PlayerBO;

import java.util.Set;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoorUnlocker {

    public static void tryToUnlock(DoorBO doorBO, PlayerBO playerBO) {
        if(!doorBO.isLocked()){
            return;
        }
        ItemBO itemKey = chooseItem(playerBO);
        if (itemKey == null) {
            return;
        }
        if (!doorBO.tryToUnlock(itemKey)) {
            log.info("\nThis is not the right item");
            return;
        }
        log.info("\nYou unlocked the door!");
        playerBO.removeItemByName(itemKey.getName());
    }

    private static ItemBO chooseItem(PlayerBO playerBO) {
        Set<String> itemsName = playerBO.getBagContent().keySet();
        if (itemsName.isEmpty()) {
            log.info("\nYou don't have any item!\nGo pick up some items!");
            return null;
        }
        String input = InputController.readChoice("Type the name of the chosen item", itemsName);
        return playerBO.getItemByName(input);
    }
}
