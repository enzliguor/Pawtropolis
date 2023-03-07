package pawtropolis.complex.game;


import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.command.domain.Command;
import pawtropolis.complex.game.command.CommandManager;
import pawtropolis.complex.game.console.InputController;
import pawtropolis.complex.game.domain.Player;
import pawtropolis.complex.map.domain.Room;
import pawtropolis.complex.map.util.MapInitializer;

@Slf4j
@ToString
@Component
public class GameController {

	private final Player player;

	private Room currentRoom;

	private boolean gameEnded;

	private final CommandManager commandManager;

	@Autowired
	private GameController(CommandManager commandManager){
		this.commandManager = commandManager;
		this.player = new Player();
		this.currentRoom = MapInitializer.populateMap();
		this.gameEnded = false;
	}

	public void runGame() {
		log.info("Type player name:");
		String playerName = InputController.readString();
		this.player.setName(playerName);

		log.info("Hello Player!\n");
		Command command = commandManager.getCommand("look");
		command.execute();

		log.info("Type help for a list of available command\n");

		while(!gameEnded) {
			log.info("Where are you going to go?");
			log.info(">");

			command = commandManager.getCommand(InputController.readString());
			if(command !=null){
				command.execute();
			}

			}

		}

	public Player getPlayer() {
		return player;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public void exitGame(){
		this.gameEnded = true;
	}
}
