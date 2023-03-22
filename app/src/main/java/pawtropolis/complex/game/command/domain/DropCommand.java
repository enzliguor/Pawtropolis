package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.map.maploader.MapInitializer;

@Slf4j
@Component("drop")
public class DropCommand extends ParameterizedCommand {

    protected DropCommand(MapInitializer mapInitializer) {
        super(mapInitializer);
    }

    @Override
    public void execute() {
        Item item = this.player.dropItemByName(parameter);
        if (item == null) {
            log.info("\nItem not found");
        } else {
            this.currentRoom.addItem(item);
        }
    }
}
