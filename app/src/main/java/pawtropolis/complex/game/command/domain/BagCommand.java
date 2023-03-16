package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.service.GameService;

import java.util.List;

@Component("bag")
@Slf4j
public class BagCommand extends Command {
    @Autowired
    protected BagCommand(GameService gameService) {
        super(gameService);
    }
    @Override
    public void execute() {
        List<String> items = this.gameService.getPlayerBagContent();

        StringBuilder builder = new StringBuilder("\nIn bag: ");

        for (String s: items) {
            builder.append(s + ", ");
        }
        if (builder.toString().endsWith(", ")) {
            builder.delete(builder.length()-2, builder.length()-1);
        }

        builder.append("   [Available Slot: " + this.gameService.getAvailableSlot() + "]");
        log.info(builder + "\n");
    }
}
