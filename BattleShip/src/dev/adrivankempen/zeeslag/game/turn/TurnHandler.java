package dev.adrivankempen.zeeslag.game.turn;

public class TurnHandler {
	private boolean turnP1;
	private boolean turnP2;
	
	public TurnHandler() {
		turnP1 = true;
		turnP2 = false;
	}
	
	/**wisselt de beurt van de spelers om*/
	public void switchTurn() {
		if(turnP1) {
			turnP1 = false;
			turnP2 = true;
		} else if(turnP2) {
			turnP1 = true;
			turnP2 = false;
		}
	}
	
	public boolean getTurnP1() {
		return turnP1;
	}
	
	public boolean getTurnP2() {
		return turnP2;
	}
}
