package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.animals.domain.Animal;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.game.service.GameService;

import java.util.List;
import java.util.Map;

@Slf4j
@Component("look")
public class LookCommand extends GameCommand {
    @Autowired
    protected LookCommand(GameService gameService) {
        super(gameService);
    }

    @Override
    public void execute() {

        Map<String, Item> itemsName = this.gameService.getRoomItems();
        Map<Class<? extends Animal>, List<Animal>> animals = this.gameService.getRoomAnimals();

        StringBuilder builder = new StringBuilder("You are in " + this.gameService.getRoomName() + ".\nItems: ");

        for (String itemName: itemsName.keySet()) {
            builder.append(itemName).append(", ");
        }
        if (builder.toString().endsWith(", ")) {
            builder.delete(builder.length()-2, builder.length()-1);
        }

        builder.append("\nNPC: ");

        for (Map.Entry<Class<? extends Animal>, List<Animal>> entry : animals.entrySet()) {
            for (Animal animal : entry.getValue()) {
                builder.append(animal.getName())
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
