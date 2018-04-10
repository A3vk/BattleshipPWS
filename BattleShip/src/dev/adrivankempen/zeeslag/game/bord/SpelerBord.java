package dev.adrivankempen.zeeslag.game.bord;

import java.util.concurrent.TimeUnit;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.AI.AI;
import dev.adrivankempen.zeeslag.states.State;

public class SpelerBord extends Bord {
	/*
	 * ai: de class die alles van de AI regelt
	 * tile: de tegel die aangevallen wordt	
	 */
	
	private AI ai;
	
	public SpelerBord(int x, int y, Handler handler) {
		super(x, y, handler);
		
		ai = new AI(this);
	}
	
	@Override
	public void tick() {
		//controleer welke fase het is
		if(handler.getSetupFase()) {
			//roep de setup op
			setup();
		} else if(handler.getAttackFase()) {
			//roep de attack op
			attack();
		}
	}
	
	public void restart() {
		super.restart();
	}
	
	/**regelt het aanvallen van de AI*/
	private void attack() {
		//is het de beurt van P2
		if(handler.getTurnP2()) {
			//Wacht 1 seconde
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {}
			//laat de AI aanvallen
			ai.attack();
				//als alle schepen gezonken zijn heeft P2 gewonnen
				if(updateShips() == 0) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {}
					handler.winP2();
					State.setState(handler.getGame().getEndGameState());
				}
				//wissel de beurt
				handler.switchTurn();
		}
	}
	
	/**regelt alles wat er in de setup op dit bord voor P1 gebeurt voor de tick()*/
	private void setup() {
		//controleer of er met de rechter muisknop gedrukt word
		if(handler.getMouseManager().getRightPressed()) {
			handler.getMouseManager().setRightPressed();
			//verander de richting
			if(direction == 0) {
				direction = 1;
			}
			else {
				direction = 0;
			}
		//controleer of er met de linker muisknop gedrukt word
		} else if(handler.getMouseManager().getLeftPressed()) {
			if(handler.getTurnP1()) {
				handler.getMouseManager().setLeftPressed();
				//plaats een schip
				if(hover() != null) {
					//plaats een schip voor player 1(0)
					placeShip(this, getTile(hover()), direction);
				}
			}				
		} else if(handler.getTurnP1()) {
			try{
				//laat een preview van het schip zien
				preview(getTile(hover()).getCoorX(), getTile(hover()).getCoorY(), this);
			} catch(NullPointerException e) {}
		}
	}
}