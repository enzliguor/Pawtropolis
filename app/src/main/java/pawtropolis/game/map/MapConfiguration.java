package pawtropolis.game.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pawtropolis.game.map.initializer.HardCodedMap;
import pawtropolis.game.map.initializer.MapFromDB;
import pawtropolis.game.map.initializer.MapInitializer;
import pawtropolis.persistence.service.RoomService;

@Configuration
public class MapConfiguration {

    @Value("${map.generation}")
    String mapGeneration;

    private final RoomService roomService;

    @Autowired
    protected MapConfiguration(RoomService roomService){
        this.roomService=roomService;
    }

    @Bean
    protected MapInitializer getMapInitializer(){
        return (mapGeneration.equals("mapFromDB"))? new MapFromDB(this.roomService) : new HardCodedMap();
    }
}
