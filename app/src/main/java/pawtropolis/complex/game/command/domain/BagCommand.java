package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.GameController;
import pawtropolis.complex.game.domain.Player;

import java.util.List;

@Component
@Slf4j
public class BagCommand extends Command {

    protected BagCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        Player player = this.gameController.getPlayer();
        List<String> items = player.getBagContent();

        StringBuilder builder = new StringBuilder("\nIn bag: ");

        for (String s: items) {
            builder.append(s + ", ");
        }
        if (builder.toString().endsWith(", ")) {
            builder.delete(builder.length()-2, builder.length()-1);
        }

        builder.append("   [Available Slot: " + player.getAvailableSlot() + "]");
        log.info(builder + "\n");
    }
}
