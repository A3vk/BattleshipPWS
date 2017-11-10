package dev.adrivankempen.zeeslag;

import dev.adrivankempen.zeeslag.input.MouseManager;

/**De Handler zorgt ervoor dat alle algemenen variabelen en inputs worden doorgegeven*/
public class Handler {
	private Game game;
	private boolean attackFase;
	
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
	
	public boolean getAttackFase() {
		return attackFase;
	}
	
	public void setAttackFase(boolean b) {
		attackFase = b;
	}
}
