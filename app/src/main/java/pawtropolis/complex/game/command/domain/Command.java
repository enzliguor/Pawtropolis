package pawtropolis.complex.game.command.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.complex.game.domain.Player;
import pawtropolis.complex.game.map.domain.Room;
import pawtropolis.complex.game.map.maploader.MapInitializer;

public abstract class Command {

    protected Player player;

    protected Room currentRoom;

    @Autowired
    protected Command(MapInitializer mapInitializer){
        this.player = new Player();
        this.currentRoom = mapInitializer.populateMap();
    }

    public abstract void execute();

}
