package pawtropolis.complex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pawtropolis.complex.game.GameController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext conf = SpringApplication.run(Application.class, args);
		GameController game =  conf.getBean(GameController.class);
		game.runGame();
	}

}
