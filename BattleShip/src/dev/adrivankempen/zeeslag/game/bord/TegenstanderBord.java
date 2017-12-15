package dev.adrivankempen.zeeslag.game.bord;

import java.util.concurrent.TimeUnit;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.AI.AI;
import dev.adrivankempen.zeeslag.states.State;

public class TegenstanderBord extends Bord{	
	/*
	 * ai: de class die alles voor de AI regelt
	 */
	private AI ai;
	
	public TegenstanderBord(int x, int y, Handler handler) {
		super(x, y, handler);
		
		ai = new AI(this);
	}
	
	@Override
	public void tick() {
		//controleer welke fase het is
		if(handler.getSetupFase()) {
			//roep de setup op
			setup();
		}else if(handler.getAttackFase()) {
			//roep de attack op
			attack();
		}
	}
	
	/**regelt de tick() van de setup voor P2*/
	private void setup() {
		//controleer ofdat P2 aan de beurt is
		if(handler.getTurnP2()) {
			//plaats een random schip
			placeShip(this, ai.randomTile(), ai.randomDirection());
		}
	}
	
	/**regelt de tick() van de aanval voor P1*/
	private void attack() {
		if(handler.getTurnP1()) {
			//controleer of er met de linker muisknop gedrukt word
			if(handler.getMouseManager().getLeftPressed()) {
				handler.getMouseManager().setLeftPressed();
				try{
					//controleer ofdat de tegel die aangevallen wordt al eens is aangevallen
					if(!getTile(hover()).getIsShot() && getTile(hover()).getCanGetShot()) {
						//val de tegel aan
						getTile(hover()).attack();
						//als alle schepen gezonken zijn heeft P1 gewonnen
						if(updateShips() == 0) {
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {}
							handler.winP1();
							State.setState(handler.getGame().getEndGameState());
						}							
						//wissel de beurt
						handler.switchTurn();
					}
				} catch(NullPointerException e) {}
				
			}
		}
	}
}
