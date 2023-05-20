package pawtropolis.game;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;
import pawtropolis.console.InputController;
import pawtropolis.game.domain.SaveBlockBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.map.initializer.MapInitializer;
import pawtropolis.persistence.service.SaveBlockService;
import pawtropolis.persistence.service.PlayerService;

import java.util.Set;

@Component
public class GameStarter implements ApplicationRunner {

    private final SaveBlockBO saveBlockBO;

    private final MapInitializer mapInitializer;

    private final SaveBlockService saveBlockService;

    private final PlayerService playerService;

    private final GameController gameController;

    public GameStarter(SaveBlockBO saveBlockBO, MapInitializer mapInitializer, SaveBlockService saveBlockService, PlayerService playerService, GameController gameController) {
        this.saveBlockBO = saveBlockBO;
        this.mapInitializer = mapInitializer;
        this.saveBlockService = saveBlockService;
        this.playerService = playerService;
        this.gameController = gameController;
    }

    public void startGame(){
        String choice = InputController.readChoice(" WELCOME TO PAWTROPOLIS", Set.of("NEW PLAYER", "REGISTERED PLAYER"));
        switch (choice) {
            case "NEW PLAYER" -> {
                PlayerBO playerBO = createPlayer();
                createNewSaveBlock(playerBO);
            }
            case "REGISTERED PLAYER" -> {
                PlayerBO playerBO = retrievePlayerFromDB();
                choice = InputController.readChoice("Welcome Back!", Set.of("NEW GAME","LOAD GAME"));
                switch (choice) {
                    case "NEW GAME" -> createNewSaveBlock(playerBO);
                    case "LOAD GAME" -> retrieveSaveBlockFromDB(playerBO);
                }
            }
        }
        if(this.saveBlockBO !=null) CustomLogger.gameMessage("\nHello " + saveBlockBO.getPlayer().getName() + "!");
        CustomLogger.gameMessage("\nType HELP for a list of available command");
        this.gameController.runGame();
    }

    private PlayerBO createPlayer() {
        String playerName = InputController.evaluateInput("Type player name:", input -> !this.playerService.existsByName(input), "An User with this name already exists");
        return PlayerBO.builder().name(playerName).build();
    }
    private void createNewSaveBlock(PlayerBO playerBO) {
        RoomBO roomBO = mapInitializer.populateMap();
        String sessionName = InputController.evaluateInput("Save Block name:",
                input -> !this.saveBlockService.existsByPlayerIdAndBlockName(playerBO.getId(), input)
                , "You already have a Save Block with this name!");

        this.saveBlockBO.initializeGameSession(playerBO, roomBO, sessionName);
    }
    private PlayerBO retrievePlayerFromDB() {
        String playerName = InputController.evaluateInput("Type Player Name:", this.playerService::existsByName, "User not found");
        return this.playerService.findByName(playerName);
    }
    private void retrieveSaveBlockFromDB(PlayerBO playerBO) {
        Set<String> sessionNames = this.saveBlockService.findBlockNamesByPlayerId(playerBO.getId());

        String sessionName = InputController.readChoice("Choose a game session", sessionNames);

        SaveBlockBO gameSession = this.saveBlockService.findByPlayerIdAndBlockName(playerBO.getId(), sessionName);
        this.saveBlockBO.initializeGameSession(gameSession);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startGame();
    }
}
