package pawtropolis.complex.game.command.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.animals.domain.Animal;
import pawtropolis.complex.game.map.maploader.MapInitializer;

import java.util.List;
import java.util.Map;

@Slf4j
@Component("look")
public class LookCommand extends Command {

    protected LookCommand(MapInitializer mapInitializer) {
        super(mapInitializer);
    }

    @Override
    public void execute() {

        List<String> itemsName = this.currentRoom.getItemsName();
        Map<Class<? extends Animal>, List<String>> animals = this.currentRoom.getAnimalsName();

        StringBuilder builder = new StringBuilder("\nYou are in " + this.currentRoom.getName() + ".\nItems: ");

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
