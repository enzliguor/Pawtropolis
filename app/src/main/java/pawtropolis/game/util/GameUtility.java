package pawtropolis.game.util;

import org.springframework.stereotype.Component;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class GameUtility {

    private static GameSessionBO gameSession;

    private GameUtility(GameSessionBO gameSessionBO) {
        gameSession = gameSessionBO;
    }

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

    public static PlayerBO getCurrentPlayer(){
        return gameSession.getPlayer();
    }

    public static RoomBO getCurrentRoom(){
        return gameSession.getCurrentRoom();
    }
}
