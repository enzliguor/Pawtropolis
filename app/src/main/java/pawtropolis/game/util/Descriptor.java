package pawtropolis.game.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pawtropolis.game.domain.LockedRoomBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.domain.animals.domain.AnimalBO;
import pawtropolis.game.map.util.CardinalPoint;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Descriptor {

    public static String getRoomDescription(RoomBO roomBO){
        Map<String, Integer> itemsName = roomBO.getItemsNameAndQuantity();
        Map<Class<? extends AnimalBO>, List<String>> animals = roomBO.getAnimalsName();
        Map<CardinalPoint, RoomBO> adjacentRooms = roomBO.getAdjacentRooms();

        StringBuilder builder = new StringBuilder("\n\nYou are in ").append(roomBO.getName()).append(".");
        StringJoiner itemJoiner = new StringJoiner(", ");
        for (Map.Entry<String, Integer> entry : itemsName.entrySet()) {
            itemJoiner.add(entry.getKey() + "(x" + entry.getValue() + ")");
        }
        builder.append("\nItems: ").append(itemJoiner);

        StringJoiner animalJoiner = new StringJoiner(", ");
        for (Map.Entry<Class<? extends AnimalBO>, List<String>> entry : animals.entrySet()) {
            String animalType = entry.getKey().getSimpleName().replaceAll("BO$","");
            for (String animalName : entry.getValue()) {
                animalJoiner.add(animalName + "(" + animalType + ")");
            }
        }
        builder.append("\nNPC: ").append(animalJoiner);

        StringJoiner roomJoiner = new StringJoiner(", \n");
        for (Map.Entry<CardinalPoint, RoomBO> entry : adjacentRooms.entrySet()){
            roomJoiner.add("- " + entry.getKey() + ((entry.getValue() instanceof LockedRoomBO)? " 'LOCKED'" : ""));
        }
        builder.append("\nAdjacent rooms:\n").append(roomJoiner).append("\n");

        return builder.toString();
    }

    public static String getBagDescription(PlayerBO playerBO){
        Map<String, Integer> items = playerBO.getBagContent();

        StringBuilder builder = new StringBuilder("\nIn bag: ");
        StringJoiner itemJoiner = new StringJoiner(", ");

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            itemJoiner.add(entry.getKey() + "(x" + entry.getValue() + ")");
        }
        builder.append(itemJoiner);

        builder.append("   [Available Slot: ")
                .append(playerBO.getAvailableSlot())
                .append("]\n");

        return builder.toString();
    }
}
