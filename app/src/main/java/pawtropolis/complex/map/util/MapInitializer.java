package pawtropolis.complex.map.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pawtropolis.complex.animals.domain.Animal;
import pawtropolis.complex.animals.domain.Eagle;
import pawtropolis.complex.animals.domain.Lion;
import pawtropolis.complex.animals.domain.Tiger;
import pawtropolis.complex.game.domain.Item;
import pawtropolis.complex.map.domain.Room;

import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapInitializer {

    public static Room populateMap() {
        Item ball = new Item("ball", "ball", 6);
        Item sword = new Item("sword", "sharpened", 10);
        Item glasses = new Item("glasses", "red", 6);
        Item hat = new Item("hat", "hat", 7);
        Item book = new Item("book", "book", 5);
        Item parruccaPlatinet = new Item("Parrucca di Platinet", "book", 5);

        Animal eva = new Tiger("Eva", "human", 4, LocalDate.now(), 135.4, 1.55, 0.51);
        Animal gina = new Tiger("Gina", "lollipop", 4, LocalDate.now(), 124.0, 1.45, 0.53);
        Animal titina = new Tiger("Titina", "hamburgher", 4, LocalDate.now(), 121.0, 1.39, 0.5);
        Animal lio = new Lion("Lio", "confetti", 4, LocalDate.now(), 130.0, 1.1, 0.49);
        Animal beppe = new Lion("Beppe", "gricia", 4, LocalDate.now(), 129.0, 1.18, 0.43);
        Animal tom = new Lion("Tom", "human", 4, LocalDate.now(), 124.0, 1.15, 0.39);
        Animal pandora = new Eagle("Pandora", "mouse", 4, LocalDate.now(), 30.0, 1.05, 1.9);
        Animal teodora = new Eagle("Teodora", "rabbit", 4, LocalDate.now(), 35.0, 0.89, 1.88);
        Animal margareth = new Eagle("Margareth", "rabbit", 4, LocalDate.now(), 28.0, 0.92, 2.1);

        Room starterRoom = new Room("Starter Room");
        Room roomSouth = new Room("Room South");
        Room roomNorth = new Room("Room North");
        Room roomEast = new Room("Room East");
        Room roomWest = new Room("Room West");

        starterRoom.addAllAnimals(List.of(eva, gina, teodora));
        roomSouth.addAllAnimals(List.of(lio, beppe));
        roomNorth.addAllAnimals(List.of(pandora, titina));
        roomEast.addAllAnimals(List.of(tom));
        roomWest.addAllAnimals(List.of(margareth));

        starterRoom.addAllItems(List.of(parruccaPlatinet, glasses, ball));
        roomSouth.addItem(sword);
        roomNorth.addItem(hat);
        roomEast.addItem(book);
        roomWest.addItem(hat);

        starterRoom.linkRoom(CardinalPoint.SOUTH, roomSouth);
        starterRoom.linkRoom(CardinalPoint.NORTH, roomNorth);
        starterRoom.linkRoom(CardinalPoint.EAST, roomEast);
        starterRoom.linkRoom(CardinalPoint.WEST, roomWest);
        roomSouth.linkRoom(CardinalPoint.NORTH, starterRoom);
        roomNorth.linkRoom(CardinalPoint.SOUTH, starterRoom);
        roomEast.linkRoom(CardinalPoint.WEST, starterRoom);
        roomWest.linkRoom(CardinalPoint.EAST, starterRoom);

        return starterRoom;
    }
}
