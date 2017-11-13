package dev.adrivankempen.zeeslag;

import dev.adrivankempen.zeeslag.input.MouseManager;

/**De Handler zorgt ervoor dat alle algemenen variabelen en inputs worden doorgegeven*/
public class Handler {
	private Game game;
	private boolean turnP1 = true, turnP2 = false;
	private boolean setupFase = true, attackFase = false;
	
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
	
	public void switchTurn() {
		turnP1 = !turnP1;
		turnP2 = !turnP2;
	}
	
	public boolean getTurnP1() {
		return turnP1;
	}
	
	public void switchFase() {
		if(setupFase) {
			setupFase = !setupFase;
			attackFase = !attackFase;
		}
	}
	
	public boolean getTurnP2() {
		return turnP2;
	}
	
	public boolean getSetupFase() {
		return setupFase;
	}
	
	public boolean getAttackFase() {
		return attackFase;
	}
}
