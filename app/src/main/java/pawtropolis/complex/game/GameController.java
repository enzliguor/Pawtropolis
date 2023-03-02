package pawtropolis.complex.game;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.complex.game.console.InputController;
import pawtropolis.complex.game.domain.Player;
import pawtropolis.complex.game.service.GameService;
import pawtropolis.complex.map.domain.Room;
import pawtropolis.complex.map.util.MapInitializer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
@ToString
public class GameController {

	private static GameController gameController;

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

	public static GameController getInstance(){
		if(gameController == null){
			return new GameController();
		}
		return gameController;
	}

	public void runGame() {
		log.info("Type player name:");
		String playerName = InputController.readString();
		Player player = new Player(playerName);

		Room entry = MapInitializer.populateMap();

		GameService service = GameService.getInstance(player, entry);

		boolean gameEnded = false;

		log.info("Hello Player!\n");
		service.look();

		log.info("Type help for a list of available command\n");

		while(!gameEnded) {
			log.info("Where are you going to go?");
			log.info(">");
			//divide the input in a command and check for another instruction
			String [] strings = InputController.readString().split("\\s", 2);
			String input = strings[0].trim();
			String instruction = (strings.length>1)? strings[1].trim() : null;
			//if the input is not equal to 'get,'go' or 'drop' and there is an instruction,
			//it means that the input is wrong
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
