package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.animals.domain.Animal;
import pawtropolis.complex.game.service.GameService;

import java.util.List;
import java.util.Map;

@Slf4j
@Component("look")
public class LookCommand extends Command {
    @Autowired
    protected LookCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {

        List<String> itemsName = this.gameService.getRoomItemsName();
        Map<Class<? extends Animal>, List<String>> animals = this.gameService.getRoomAnimalsName();

        StringBuilder builder = new StringBuilder("\nYou are in " + this.gameService.getRoomName() + ".\nItems: ");

        for (String itemName: itemsName) {
            builder.append(itemName).append(", ");
        }
        if (builder.toString().endsWith(", ")) {
            builder.delete(builder.length()-2, builder.length()-1);
        }

        builder.append("\nNPC: ");

        for (Map.Entry<Class<? extends Animal>, List<String>> entry : animals.entrySet()) {
            for (String animalName : entry.getValue()) {
                builder.append(animalName)
                        .append("(").append(entry.getKey().getSimpleName()).append(")");
                builder.append(", ");
            }
        }
        if (builder.toString().endsWith(", ")) {
            builder.delete(builder.length()-2, builder.length()-1);
        }
        log.info(builder + "\n");
    }
}
