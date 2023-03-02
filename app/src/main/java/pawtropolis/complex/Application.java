package pawtropolis.complex;

import pawtropolis.complex.game.GameController;

public class Application {

	public static void main(String[] args) {
		GameController.getInstance().runGame();
	}

}
