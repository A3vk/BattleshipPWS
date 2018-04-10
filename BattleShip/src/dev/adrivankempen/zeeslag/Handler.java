package dev.adrivankempen.zeeslag;

import dev.adrivankempen.zeeslag.input.MouseManager;

/**De Handler zorgt ervoor dat alle algemenen variabelen en inputs worden doorgegeven*/
public class Handler {
	private Game game;
	private boolean turnP1 = true, turnP2 = false;
	private boolean setupFase = true, attackFase = false;
	private boolean winP1 = false, winP2 = false;
	private boolean restart = false;
	
	public Handler(Game game) {
		this.game = game;	
	}
	
	public void restart() {
		turnP1 = true;
		turnP2 = false;
		setupFase = true;
		attackFase = false;
		winP1 = false;
		winP2 = false;
		restart = false;
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
	
	public void resetWin() {
		winP1 = false;
		winP2 = false;
	}
	
	public boolean getWinP1() {
		return winP1;
	}
	
	public boolean getWinP2() {
		return winP2;
	}
	
	public void winP1() {
		winP1 = true;
	}
	
	public void winP2() {
		winP2 = true;
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
	
	public void setRestart() {
		restart = true;
	}
	
	public boolean getRestart() {
		return restart;
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
