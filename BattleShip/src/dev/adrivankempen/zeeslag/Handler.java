package dev.adrivankempen.zeeslag;

import dev.adrivankempen.zeeslag.input.MouseManager;

public class Handler {
	private Game game;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
