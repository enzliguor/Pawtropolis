package pawtropolis.game;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pawtropolis.console.CustomLogger;
import pawtropolis.console.InputController;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;
import pawtropolis.game.map.initializer.MapInitializer;
import pawtropolis.persistence.service.GameSessionService;
import pawtropolis.persistence.service.PlayerService;

import java.util.List;

@Component
public class GameStarter implements ApplicationRunner {

    private final GameSessionBO gameSessionBO;

    private final MapInitializer mapInitializer;

    private final GameSessionService gameSessionService;

    private final PlayerService playerService;

    private final GameController gameController;

    public GameStarter(GameSessionBO gameSessionBO, MapInitializer mapInitializer, GameSessionService gameSessionService, PlayerService playerService, GameController gameController) {
        this.gameSessionBO = gameSessionBO;
        this.mapInitializer = mapInitializer;
        this.gameSessionService = gameSessionService;
        this.playerService = playerService;
        this.gameController = gameController;
    }

    public void startGame(){
        String choice = InputController.readChoice(" WELCOME TO PAWTROPOLIS", List.of("NEW PLAYER", "REGISTERED PLAYER"));
        switch (choice) {
            case "NEW PLAYER" -> {
                PlayerBO playerBO = createPlayer();
                createNewGameSession(playerBO);
            }
            case "REGISTERED PLAYER" -> {
                PlayerBO playerBO = retrievePlayerFromDB();
                choice = InputController.readChoice("Welcome Back!", List.of("NEW GAME","LOAD GAME"));
                switch (choice) {
                    case "NEW GAME" -> createNewGameSession(playerBO);
                    case "LOAD GAME" -> retrieveGameSessionFromDB(playerBO);
                }
            }
        }
        if(this.gameSessionBO!=null) CustomLogger.gameMessage("\nHello " + gameSessionBO.getPlayer().getName() + "!");
        CustomLogger.gameMessage("\nType HELP for a list of available command");
        this.gameController.runGame();
    }

    private PlayerBO createPlayer() {
        String playerName = InputController.evaluateInput("Type player name:", input -> !this.playerService.existsByName(input), "An User with this name already exists");
        return PlayerBO.builder().name(playerName).build();
    }
    private void createNewGameSession(PlayerBO playerBO) {
        RoomBO roomBO = mapInitializer.populateMap();
        String sessionName = InputController.evaluateInput("Save Block name:",
                input -> !this.gameSessionService.existsByPlayerIdAndSessionName(playerBO.getId(), input)
                , "You already have a Save Block with this name!");

        this.gameSessionBO.initializeGameSession(playerBO, roomBO, sessionName);
    }
    private PlayerBO retrievePlayerFromDB() {
        String playerName = InputController.evaluateInput("Type Player Name:", this.playerService::existsByName, "User not found");
        return this.playerService.findByName(playerName);
    }
    private void retrieveGameSessionFromDB(PlayerBO playerBO) {
        List<String> sessionNames = this.gameSessionService.findSessionNamesByPlayerId(playerBO.getId());

        String sessionName = InputController.readChoice("Choose a game session", sessionNames);

        GameSessionBO gameSession = this.gameSessionService.findByPlayerIdAndSessionName(playerBO.getId(), sessionName);
        this.gameSessionBO.initializeGameSession(gameSession);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startGame();
    }
}
