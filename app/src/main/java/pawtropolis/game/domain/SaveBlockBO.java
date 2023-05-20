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
public class SaveBlockBO implements BusinessObject {

    private Long id;
    private String name;
    private PlayerBO player;
    private RoomBO currentRoom;

    public void initializeGameSession(PlayerBO player, RoomBO room, String sessionName){
            this.player = player;
            this.currentRoom = room;
            this.name = sessionName;
    }
    public void initializeGameSession(SaveBlockBO saveBlockBO){
            this.id = saveBlockBO.getId();
            this.name = saveBlockBO.getName();
            this.player = saveBlockBO.getPlayer();
            this.currentRoom = saveBlockBO.getCurrentRoom();
    }

    public boolean isNotPersisted(){
        return this.id==null;
    }
}
