package pawtropolis.game.command.domain.gamecommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.LockedRoomBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.domain.animals.domain.AnimalBO;
import pawtropolis.game.map.util.CardinalPoint;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@Component
public class LookCommand extends GameCommand {

    protected LookCommand(GameSessionBO gameSessionBO) {
        super(gameSessionBO);
    }

    @Override
    public void execute() {
        RoomBO currentRoom = gameSessionBO.getCurrentRoom();
        Map<String, Integer> itemsName = currentRoom.getItemsName();
        Map<Class<? extends AnimalBO>, List<String>> animals = currentRoom.getAnimalsName();
        Map<CardinalPoint, RoomBO> adjacentRooms = currentRoom.getAdjacentRooms();

        StringBuilder builder = new StringBuilder("\n\nYou are in ").append(currentRoom.getName()).append(".");
        StringJoiner itemJoiner = new StringJoiner(", ");
        for (Map.Entry<String, Integer> entry : itemsName.entrySet()) {
            itemJoiner.add(entry.getKey() + "(x" + entry.getValue() + ")");
        }
        builder.append("\nItems: ").append(itemJoiner.toString());

        StringJoiner animalJoiner = new StringJoiner(", ");
        for (Map.Entry<Class<? extends AnimalBO>, List<String>> entry : animals.entrySet()) {
            String animalType = entry.getKey().getSimpleName().replaceAll("BO$","");
            for (String animalName : entry.getValue()) {
                animalJoiner.add(animalName + "(" + animalType + ")");
            }
        }
        builder.append("\nNPC: ").append(animalJoiner.toString());

        StringJoiner roomJoiner = new StringJoiner(", \n");
        for (Map.Entry<CardinalPoint, RoomBO> entry : adjacentRooms.entrySet()){
            roomJoiner.add("- " + entry.getKey() + ((entry.getValue() instanceof LockedRoomBO)? " 'LOCKED'" : ""));
        }
        builder.append("\nAdjacent rooms:\n").append(roomJoiner.toString()).append("\n");

        log.info(builder.toString());

    }
}
