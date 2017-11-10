package dev.adrivankempen.zeeslag;

public class Launcher {
	/**Hoofd bestand dat de game volledig opstart*/
	public static void main(String[] args) {
		Game game = new Game("Zeeslag", 900, 460);
		game.start();
	}
}
