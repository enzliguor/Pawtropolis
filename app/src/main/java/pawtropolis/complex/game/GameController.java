package pawtropolis.complex.game;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pawtropolis.complex.game.console.InputController;
import pawtropolis.complex.game.service.GameService;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
@ToString
@Controller
public class GameController {

	private GameService service;

	@Autowired
	public GameController(GameService service){
		this.service = service;
	}

	private static final String HELP = """
				look             -> get a description of the current Room
				bag              -> show the content of your bag
				get 'item name'  -> collect an item from the room
				drop 'item name' -> drop an item from your bag
				go 'direction'   -> move into another adjacent room, if available (north, south, east, west)
				exit             -> close the game
				""";

	private static final String WRONG_COMMAND =  """
				Unrecognized command
				Type 'help' for a list of available command
				""";

	public void runGame() {
		log.info("Type player name:");
		String playerName = InputController.readString();
		service.setPlayerName(playerName);

		boolean gameEnded = false;

		log.info("Hello Player!\n");
		service.look();

		log.info("Type help for a list of available command\n");

		while(!gameEnded) {
			log.info("Where are you going to go?");
			log.info(">");

			String [] strings = InputController.readString().split("\\s", 2);
			String input = strings[0].trim();
			String instruction = (strings.length>1)? strings[1].trim() : null;

			if (!input.equals("get") && !input.equals("go") && !input.equals("drop") && instruction != null) {
				log.info(WRONG_COMMAND);
			}else {
				switch (input) {
					case "look"  ->	service.look();
					case "bag"	 ->	service.showBagContent();
					case "get"   -> service.collectItemByName(instruction);
					case "drop"  -> service.dropItemByName(instruction);
					case "go"	 ->	service.goToAnAdjacentRoom(instruction);
					case "help"	 -> log.info(HELP);
					case "exit"  -> gameEnded = true;
					default      -> log.info(WRONG_COMMAND);
				}
			}

		}
	}
}
