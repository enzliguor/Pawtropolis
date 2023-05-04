package pawtropolis.game.command.domain.gamecommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.PlayerBO;

import java.util.Map;

@Component
@Slf4j
public class BagCommand extends GameCommand {

    protected BagCommand(GameSessionBO gameSessionBO) {
        super(gameSessionBO);
    }

    @Override
    public void execute() {
        PlayerBO player = this.gameSessionBO.getPlayer();
        Map<String, Integer> items = player.getBagContent();

        StringBuilder builder = new StringBuilder("\nIn bag: ");

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            builder.append(entry.getKey())
                    .append("(x").append(entry.getValue())
                    .append("), ");
        }
        if (builder.toString().endsWith(", ")) {
            builder.delete(builder.length()-2, builder.length()-1);
        }

        builder.append("   [Available Slot: ")
                .append(player.getAvailableSlot())
                .append("]");
        log.info(builder + "\n");
    }
}
