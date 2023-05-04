package pawtropolis.game.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Component
public class GameSessionBO implements BusinessObject {

    private Long id;
    private String sessionName;
    private PlayerBO player;
    private RoomBO currentRoom;

    public void initializeGameSession(PlayerBO player, RoomBO room, String sessionName){
            this.player = player;
            this.currentRoom = room;
            this.sessionName = sessionName;
    }
    public void initializeGameSession(GameSessionBO gameSessionBO){
            this.id = gameSessionBO.getId();
            this.sessionName = gameSessionBO.getSessionName();
            this.player = gameSessionBO.getPlayer();
            this.currentRoom = gameSessionBO.getCurrentRoom();
    }

    public boolean isNotPersisted(){
        return this.id==null;
    }
}
