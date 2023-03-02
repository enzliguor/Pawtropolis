package pawtropolis.complex.map.util;

import lombok.Getter;

import java.util.Arrays;

public enum CardinalPoint {
    NORTH("north"),
    EAST("east"),
    SOUTH("south"),
    WEST("west");

    @Getter
    private final String name;
    private CardinalPoint(String cardinalPointName) {
        this.name=cardinalPointName;
    }

    public static CardinalPoint of(String cardinalPoint){
        return Arrays.stream(CardinalPoint.values())
                .filter(cardinalPoint1 -> cardinalPoint1.name.equalsIgnoreCase(cardinalPoint))
                .findFirst()
                .orElse(null);
    }
}
