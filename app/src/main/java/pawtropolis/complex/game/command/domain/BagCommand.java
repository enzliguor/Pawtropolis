package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.map.maploader.MapInitializer;

import java.util.List;

@Component("bag")
@Slf4j
public class BagCommand extends Command {

    protected BagCommand(MapInitializer mapInitializer) {
        super(mapInitializer);
    }

    @Override
    public void execute() {
        List<String> items = this.player.getBagContent();

        StringBuilder builder = new StringBuilder("\nIn bag: ");

        for (String s: items) {
            builder.append(s + ", ");
        }
        if (builder.toString().endsWith(", ")) {
            builder.delete(builder.length()-2, builder.length()-1);
        }

        builder.append("   [Available Slot: " + this.player.getAvailableSlot() + "]");
        log.info(builder + "\n");
    }
}
