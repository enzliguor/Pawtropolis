package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.map.maploader.MapInitializer;

@Slf4j
@Component("get")
public class GetCommand extends ParameterizedCommand {

    protected GetCommand(MapInitializer mapInitializer) {
        super(mapInitializer);
    }

    @Override
    public void execute() {
        Item item = this.currentRoom.findItemByName(parameter);
        if (item == null) {
            log.info("\nItem not found\n");
        }else if(this.player.checkItemFitsInBag(item)){
            this.player.collectItem(item);
            this.currentRoom.removeItem(item);
        }else{
            log.info("\nYour Bag is too full! \n" +
                    "Free up " +
                    (item.getSlotRequired() - this.player.getAvailableSlot()) +
                    " slots to get this item\n");
        }
    }
}
