package pawtropolis.game.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pawtropolis.game.domain.ItemBO;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameUtility {

    public static ItemBO findItemByName(String itemName, Collection<ItemBO> items) {
        return items.stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);
    }

    public static Map<String, Integer> getItemsNameAndQuantity(Map<ItemBO, Integer> items) {
        return items.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Map.Entry::getValue
                ));
    }
}
